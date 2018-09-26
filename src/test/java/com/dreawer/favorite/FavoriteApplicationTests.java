package com.dreawer.favorite;

import com.dreawer.favorite.form.AddFavoriteForm;
import com.dreawer.favorite.form.AddFavoritesForm;
import com.dreawer.favorite.form.UpdateFavoritesForm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.UUID;
import static com.dreawer.favorite.constants.ControllerConstants.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FavoriteApplicationTests {

    @Autowired
    private WebApplicationContext context;
    private MockMvc mvc;

    @Before
    public void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(context).build();//建议使用这种
    }


    @Test
    public void testTicket() throws  Exception{
       /* //测试添加收藏夹
        AddFavoritesForm form = new AddFavoritesForm();
        form.setName("测试添加收藏夹002");
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(form);
        mvc.perform(MockMvcRequestBuilders.post(REQ_FAVORITES_ADD)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(requestJson)
                .header("userId", UUID.randomUUID().toString().replace("-", ""))
                .sessionAttr("111", "111"))
                .andDo(print());*/


       /* //测试删除收藏夹
        UpdateFavoritesForm form = new UpdateFavoritesForm();
        form.setId("9bb5dedb2e514befbbd5b8ef46440a30");
        form.setName("测试收藏夹001");

        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(form);
        mvc.perform(MockMvcRequestBuilders.post(REQ_FAVORITES_DELETE)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(requestJson)
                //.header("userId", UUID.randomUUID().toString().replace("-", ""))
                .header("userId", "b67d3a5790374bda97e43c45f60ccb78")
                .sessionAttr("111", "111"))
                .andDo(print());
*/

        /*//测试更新收藏夹
        UpdateFavoritesForm form = new UpdateFavoritesForm();
        form.setId("3082f2be9e7f456bb35583f264203b96");
        form.setName("修改测试收藏夹001");

        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(form);
        mvc.perform(MockMvcRequestBuilders.post(REQ_FAVORITES_EDIT)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(requestJson)
                //.header("userId", UUID.randomUUID().toString().replace("-", ""))
                .header("userId", "cb46ba341d6f4de0adfd980d8b8246be")
                .sessionAttr("111", "111"))
                .andDo(print());*/


        //测试获取收藏夹列表
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        mvc.perform(MockMvcRequestBuilders.get(REQ_FAVORITES_LIST)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                //.header("userId", UUID.randomUUID().toString().replace("-", ""))
                .header("userId", "84ba09c6c6ef42f1941b75a27be0eedd")
                .sessionAttr("111", "111"))
                .andDo(print());



        /*//测试收藏内容
        AddFavoriteForm form = new AddFavoriteForm();
        form.setObjectId("345");
        form.setCategory("测试类别1");

        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(form);
        mvc.perform(MockMvcRequestBuilders.post(REQ_FAVORITE)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(requestJson)
                //.header("userId", UUID.randomUUID().toString().replace("-", ""))
                .header("userId", "84ba09c6c6ef42f1941b75a27be0eedd")
                .sessionAttr("111", "111"))
                .andDo(print());*/


        /*//测试取消收藏
        AddFavoriteForm form = new AddFavoriteForm();
        form.setObjectId("123");
        form.setCategory("测试类别");
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(form);
        mvc.perform(MockMvcRequestBuilders.post(REQ_UNFAVORITE)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(requestJson)
                //.header("userId", UUID.randomUUID().toString().replace("-", ""))
                .header("userId", "c957822db95c4d92834ea9ed5b08c096")
                .sessionAttr("111", "111"))
                .andDo(print());*/



        /*//测试获取指定收藏者的收藏信息
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        mvc.perform(MockMvcRequestBuilders.get(REQ_GET_USERFAVORITES)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                //.header("userId", UUID.randomUUID().toString().replace("-", ""))
                .header("userId", "9d331de366ac4bb08bd1063170842c13")
                .param("pageSize","2")
                .sessionAttr("111", "111"))
                .andDo(print());*/


        /*//测试更新收藏（收藏分类）
        AddFavoriteForm form = new AddFavoriteForm();
        form.setObjectId("123");
        form.setCategory("修改测试类别");
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(form);
        mvc.perform(MockMvcRequestBuilders.post(REQ_UPDATE_FAVORITE)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(requestJson)
                //.header("userId", UUID.randomUUID().toString().replace("-", ""))
                .header("userId", "9d331de366ac4bb08bd1063170842c13")
                .sessionAttr("111", "111"))
                .andDo(print());*/


    }

}
