package com.lzh.jdh.qa.commons.core.utils;

import com.google.common.base.Charsets;
import org.apache.commons.collections4.MapUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

/**
 * http请求工具
 * @Author: liuzhanhui
 * @Decription:
 * @Date: Created in 2019-09-18:00:23
 * Modify date: 2019-09-18:00:23
 */
public class HttpRequestUtil {

    private static Logger log = LoggerFactory.getLogger(HttpRequestUtil.class);
    /**
     * 发送get请求
     * @param headers
     * @param cookies
     * @param path
     * @param connTime
     * @param parametersBody
     * @return
     * @throws URISyntaxException
     */
    public static String getRequest(Map<String,String> headers, Map<String,String> cookies, String path, Integer connTime, List<NameValuePair> parametersBody) throws URISyntaxException {
        log.info("[getRequest] resourceUrl: {}", path);
        if (connTime == null)
            connTime = 1000;
        URIBuilder uriBuilder = new URIBuilder(path);
        if (parametersBody != null && parametersBody.size()>0)
            uriBuilder.setParameters(parametersBody);
        HttpGet get = new HttpGet(uriBuilder.build());
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(connTime).setConnectTimeout(connTime)
                .setRedirectsEnabled(true)
                .build();
        get.setConfig(requestConfig);
        if (MapUtils.isNotEmpty(headers)) {
            headers.forEach(
                    (k, v) -> {
                        get.setHeader(k, v);
                    }
            );
        }
        String cs = "";
        if (MapUtils.isNotEmpty(cookies)){
            for (Map.Entry<String,String> entry : cookies.entrySet()){
                cs += entry.getKey()+"="+entry.getValue()+";";
            }
            get.addHeader("Cookie",cs);
        }
        HttpClient client = HttpClientBuilder.create().build();
        try {
            HttpResponse response = client.execute(get);
            int code = response.getStatusLine().getStatusCode();
            if (code >= 400)
                throw new RuntimeException((new StringBuilder()).append("Could not access protected resource. Server returned http code: ").append(code).toString());
            log.info("请求返回的结果：{}", EntityUtils.toString(response.getEntity()));
            return EntityUtils.toString(response.getEntity());
        }
        catch (ClientProtocolException e) {
            log.error("postRequest -- Client protocol exception!"+e.getMessage());
            throw new RuntimeException("postRequest -- Client protocol exception!", e);
        }
        catch (IOException e) {
            log.error("请求错误："+e.getMessage());
            throw new RuntimeException("postRequest -- IO error!", e);
        }
        finally {
            log.error("释放请求连接");
            get.releaseConnection();
        }
    }

    /**
     * 发送post请求(表单请求)
     * @param path
     * @param parametersBody
     * @param headers
     * @param cookies
     * @param connTime
     * @return
     */
    public static String postForm(String path, List<NameValuePair> parametersBody,Map<String,String> headers,Map<String,String> cookies,Integer connTime) {
        if (connTime == null)
            connTime = 1000;
        HttpEntity entity = new UrlEncodedFormEntity(parametersBody, Charsets.UTF_8);
        return postRequest(headers,cookies,connTime,path, "application/x-www-form-urlencoded", entity);
    }


    /**
     * 发送post请求(json格式)
     * @param path
     * @param json
     * @param headers
     * @param cookies
     * @param connTime
     * @return
     */
    public static String postJSON(String path, String json,Map<String,String> headers,Map<String,String> cookies,Integer connTime)  {
        if (connTime == null)
            connTime = 1000;
        StringEntity entity = new StringEntity(json, Charsets.UTF_8);
        entity.setContentType("application/json");
        return postRequest(headers,cookies,connTime,path, "application/json", entity);
    }


    /**
     * 发送post请求
     * @param headers
     * @param cookies
     * @param connTime
     * @param path
     * @param mediaType
     * @param entity
     * @return
     */
    public static String postRequest(Map<String,String> headers,Map<String,String> cookies,Integer connTime,String path, String mediaType, HttpEntity entity) {
        log.info("[postRequest] resourceUrl: {}", path);
        if (connTime == null)
            connTime = 1000;
        HttpPost post = new HttpPost(path);
        post.addHeader("Content-Type", mediaType);
        post.addHeader("Accept", "application/json");
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(connTime).setConnectTimeout(connTime)
                .setRedirectsEnabled(true)
                .build();
        post.setConfig(requestConfig);
        if (MapUtils.isNotEmpty(headers)){
            headers.forEach(
                    (k, v) -> {
                        post.setHeader(k, v);
                    }
            );
        }
        String cs = "";
        if (MapUtils.isNotEmpty(cookies)){
            for (Map.Entry<String,String> entry : cookies.entrySet()){
                cs += entry.getKey()+"="+entry.getValue()+";";
            }
            post.addHeader("Cookie",cs);
        }
        post.setEntity(entity);
        try {
            CloseableHttpClient client = HttpClients.createDefault();
            HttpResponse response = client.execute(post);
            int code = response.getStatusLine().getStatusCode();
            if (code >= 400) {
                log.error("post请求地址：{},返回code码：{}",path,code);
                throw new RuntimeException(EntityUtils.toString(response.getEntity()));
            }
            log.info("请求返回结果：{}",EntityUtils.toString(response.getEntity()));
            return EntityUtils.toString(response.getEntity());
        }
        catch (ClientProtocolException e) {
            log.error("post请求地址：{},错误信息：{}",path,e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
        catch (IOException e) {
            log.error("post请求地址：{},错误信息：{}",path,e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
        finally {
            post.releaseConnection();
        }
    }




}
