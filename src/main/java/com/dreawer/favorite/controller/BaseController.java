package com.dreawer.favorite.controller;

import com.dreawer.responsecode.rcdt.Error;
import com.dreawer.responsecode.rcdt.ResponseCode;
import com.dreawer.responsecode.rcdt.ResponseCodeRepository;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import static org.apache.commons.lang.StringUtils.isBlank;

/**
 * <code>BaseController</code> 它是本系统中所有控制器的基类，提供控制器通用方法的实现。
 *
 * @author David Dai
 * @version 1.0
 * @since Dreawer 1.0
 */
public class BaseController {

    @Autowired
    RestTemplate restTemplate;

    // --------------------------------------------------------------------------------
    // 其他
    // --------------------------------------------------------------------------------


    /**
     * 将指定（带空格的）关键词以 % 链接为关键词字符串。
     *
     * @param keywords 关键词数组。
     * @return 关键词字符串。
     * @author David Dai
     * @since 2.0
     */
    protected String getKeyword(String keywords) {
        if (isBlank(keywords)) {
            return keywords;
        }
        return keywords.replace(" ", "%");
    }

    /**
     * 获取当前系统时间。
     *
     * @return 当前系统时间。
     * @author David Dai
     * @since 2.0
     */
    protected Timestamp getNow() {
        return new Timestamp(System.currentTimeMillis());
    }


    /**
     * 获取request中的json参数。
     *
     * @param req
     * @return
     * @throws IOException
     */
    protected String getRequestJson(HttpServletRequest req) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader in = req.getReader();
        String line;
        while ((line = in.readLine()) != null) {
            sb.append(line);
        }
        return sb.toString();
    }


    public Boolean verifyPhone(String phoneNumber, String code, String userId) {
        Map<String, Object> data = new HashMap<>();
        //data.put("module","MODULE_VERIF_CODE");
        //data.put("senderId",senderId);
        data.put("address", phoneNumber);
        data.put("code", code);
        String response = restPost("http://nc/api/checkedVerifyCode", data, userId);
        ResponseCode responseCode = ResponseCode.instanceOf(response);
        System.out.println(response);
        if (!responseCode.getCode().equals("000000")) {
            return false;
        }
        return true;
    }

    public String restPost(String url, Object data, String userId) {
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.set("userId", userId);
        Gson gson = new Gson();
        String json = gson.toJson(data);
        HttpEntity<String> entity = new HttpEntity<String>(json, headers);
        String response = restTemplate.postForObject(url, entity, String.class);
        System.out.println(response);
        return response;
    }

    /**
     * restGET请求
     * 注意在getForObject传URLVariable时需要在url中指定占位符
     * 如http://xxx.com?id={A} map.put("id",id)
     *
     * @param url
     * @return
     */
    public String restGet(String url) {
        String response = restTemplate.getForObject(url, String.class);
        return response;

    }


    protected ResponseCode checkErrors(BindingResult result) {
        return ResponseCodeRepository.fetch(result.getFieldError().getDefaultMessage(), result.getFieldError().getField(), Error.ENTRY);
    }
}
