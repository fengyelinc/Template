package com.example.demo.utils;


import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.nio.charset.Charset;


@Slf4j
public class WebServiceUtils {


    public static String doPostByXml(String url, String xmlstr){
        CloseableHttpClient httpclient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(url);
        try {
            post.addHeader("Content-type","text/xml; charset=UTF-8");
            post.setEntity(new StringEntity(xmlstr, Charset.forName("UTF-8")));

            CloseableHttpResponse res = httpclient.execute(post);
            int code = res.getStatusLine().getStatusCode();
            if (code == 200) {
                log.info("webservice接口对接成功！！！！！！！！！！！");
                return EntityUtils.toString(res.getEntity(), "UTF-8");
            }
            if (res != null) {
                res.close();
            }
            if (httpclient != null) {
                httpclient.close();
            }
        } catch (Exception e) {
            log.error(e.getMessage());
           e.printStackTrace();
        }
        return "";
    }



}
