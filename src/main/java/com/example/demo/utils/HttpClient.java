package com.example.demo.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @program: myp
 * @description
 * @author: YouName
 * @create: 2019-12-27 14:22
 **/
public class HttpClient {

    static int socketTimeout = 30000;// 请求超时时间
    static int connectTimeout = 30000;// 传输超时时间

    /**
     * post请求传输map数据
     *
     * @param url
     * @param map
     * @param encoding
     * @return
     * @throws ClientProtocolException
     * @throws IOException
     */
    public static String sendPostDataByMap(String url, Map<String, String> map, String encoding) throws ClientProtocolException, IOException {
        String result = "";

        // 创建httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        // 创建post方式请求对象
        HttpPost httpPost = new HttpPost(url);

        // 装填参数
        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        if (map != null) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                nameValuePairs.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
        }

        // 设置参数到请求对象中
        httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, encoding));

        // 设置header信息
        // 指定报文头【Content-type】、【User-Agent】
        httpPost.setHeader("Content-type", "application/x-www-form-urlencoded");
        httpPost.setHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");

        // 执行请求操作，并拿到结果（同步阻塞）
        CloseableHttpResponse response = httpClient.execute(httpPost);
        // 获取结果实体
        // 判断网络连接状态码是否正常(0--200都数正常)
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            result = EntityUtils.toString(response.getEntity(), "GB2312");
        }
        // 释放链接
        response.close();

        return result;
    }

    /**
     * post请求传输json数据
     *
     * @param url
     * @param json
     * @param encoding
     * @return
     * @throws ClientProtocolException
     * @throws IOException
     */
    public static String sendPostDataByJson(String url, String json, String encoding) throws ClientProtocolException, IOException {
        String result = "";

        // 创建httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();

        // 创建post方式请求对象
        HttpPost httpPost = new HttpPost(url);

        // 设置参数到请求对象中
        StringEntity stringEntity = new StringEntity(json, ContentType.APPLICATION_JSON);
        stringEntity.setContentEncoding("utf-8");
        httpPost.setEntity(stringEntity);

        // 执行请求操作，并拿到结果（同步阻塞）
        CloseableHttpResponse response = httpClient.execute(httpPost);

        // 获取结果实体
        // 判断网络连接状态码是否正常(0--200都数正常)
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            result = EntityUtils.toString(response.getEntity(), "GB2312");
        }
        // 释放链接
        response.close();

        return result;
    }


    public static int doPostByXml(String url, String xmlstr){
        CloseableHttpClient httpclient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(url);
        JSONObject response = null;
        try {
            post.addHeader("Content-type","application/xml; charset=UTF-8");
            post.addHeader("serviceID","db65b1bc7d604a539d0a60cd6daa2d2d");
            post.addHeader("secretKey","49d62608c978688c7bb9d8aa751e7fbd");
            post.addHeader("requestType","service");
            post.setEntity(new StringEntity(xmlstr, Charset.forName("UTF-8")));

            CloseableHttpResponse res = httpclient.execute(post);
            int code = res.getStatusLine().getStatusCode();
            res.close();
            return code;
        } catch (Exception e) {
            return -1;
        }

    }


    public static int putHttpPost(String url){
        CloseableHttpClient httpclient = HttpClientBuilder.create().build();
        HttpPut put = new HttpPut(url);
        JSONObject response = null;
        try {
            put.addHeader("Content-type","application/xml; charset=UTF-8");
            put.addHeader("serviceID","db65b1bc7d604a539d0a60cd6daa2d2d");
            put.addHeader("secretKey","49d62608c978688c7bb9d8aa751e7fbd");
            put.addHeader("requestType","service");
//			post.setEntity(new StringEntity(json.toString(), Charset.forName("UTF-8")));

            CloseableHttpResponse res = httpclient.execute(put);
            int code = res.getStatusLine().getStatusCode();
            res.close();
            return code;
        } catch (Exception e) {
            return -1;
        }

    }


    /**
     * 使用SOAP1.1发送消息
     *
     * @param postUrl
     * @param soapXml
     * @param soapAction
     * @return
     */
    public static String doPostSoap1_1(String postUrl, String soapXml,
                                       String soapAction) {
        String retStr = "";
        // 创建HttpClientBuilder
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        // HttpClient
        CloseableHttpClient closeableHttpClient = httpClientBuilder.build();
        HttpPost httpPost = new HttpPost(postUrl);
        //  设置请求和传输超时时间
        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(socketTimeout)
                .setConnectTimeout(connectTimeout).build();
        httpPost.setConfig(requestConfig);
        try {
            httpPost.setHeader("Content-Type", "text/xml;charset=UTF-8");
            httpPost.setHeader("SOAPAction", soapAction);
            StringEntity data = new StringEntity(soapXml,
                    Charset.forName("UTF-8"));
            httpPost.setEntity(data);
            CloseableHttpResponse response = closeableHttpClient
                    .execute(httpPost);
            HttpEntity httpEntity = response.getEntity();
            if (httpEntity != null) {
                // 打印响应内容
                retStr = EntityUtils.toString(httpEntity, "UTF-8");
//                logger.info("response:" + retStr);
            }
            // 释放资源
            closeableHttpClient.close();
        } catch (Exception e) {
//            logger.error("exception in doPostSoap1_1", e);
        }
        return retStr;
    }

    /**
     * 使用SOAP1.2发送消息
     *
     * @param postUrl
     * @param soapXml
     * @param soapAction
     * @return
     */
    public static String doPostSoap1_2(String postUrl, String soapXml,
                                       String soapAction) {
        String retStr = "";
        // 创建HttpClientBuilder
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        // HttpClient
        CloseableHttpClient closeableHttpClient = httpClientBuilder.build();
        HttpPost httpPost = new HttpPost(postUrl);
        // 设置请求和传输超时时间
        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(socketTimeout)
                .setConnectTimeout(connectTimeout).build();
        httpPost.setConfig(requestConfig);
        try {
            httpPost.setHeader("Content-Type",
                    "application/soap+xml;charset=UTF-8");
            httpPost.setHeader("SOAPAction", soapAction);
            StringEntity data = new StringEntity(soapXml,
                    Charset.forName("UTF-8"));
            httpPost.setEntity(data);
            CloseableHttpResponse response = closeableHttpClient
                    .execute(httpPost);
            HttpEntity httpEntity = response.getEntity();
            if (httpEntity != null) {
                // 打印响应内容
                retStr = EntityUtils.toString(httpEntity, "UTF-8");
//                logger.info("response:" + retStr);
            }
            // 释放资源
            closeableHttpClient.close();
        } catch (Exception e) {
//            logger.error("exception in doPostSoap1_2", e);
        }
        return retStr;
    }



}
