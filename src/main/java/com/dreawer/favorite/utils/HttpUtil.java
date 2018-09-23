package com.dreawer.favorite.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

/**
 * Http请求工具类
 */
public class HttpUtil {
    private HttpUtil() {

    }

    /**
     * 发送post请求
     *
     * @param url
     * @param header
     * @param body
     * @return
     */
    public static String doPost(String url, Map<String, String> header, String body) {
        String result = "";
        BufferedReader in = null;
        PrintWriter out = null;
        try {
            // 设置 url
            URL realUrl = new URL(url);
            URLConnection connection = realUrl.openConnection();
            // 设置 header
            for (String key : header.keySet()) {
                connection.setRequestProperty(key, header.get(key));
            }
            // 设置请求 body
            connection.setDoOutput(true);
            connection.setDoInput(true);
            out = new PrintWriter(connection.getOutputStream());
            // 保存body
            out.print(body);
            // 发送body
            out.flush();
            // 获取响应body
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            return null;
        }
        return result;
    }

    /**
     * 发送get请求
     *
     * @param url
     * @param header
     * @return
     */
    public static String doGet(String url, Map<String, String> header) {
        String result = "";
        BufferedReader in = null;
        try {
            // 设置 url
            URL realUrl = new URL(url);
            URLConnection connection = realUrl.openConnection();
            // 设置 header
            for (String key : header.keySet()) {
                connection.setRequestProperty(key, header.get(key));
            }
            // 设置请求 body
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            return null;
        }
        return result;
    }
}
