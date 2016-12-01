package com.yaofei.framework.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.util.*;
import java.util.Map.Entry;

/*
 * 利用HttpClient进行post请求的工具类
 */
public class HttpClientUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpClientUtil.class);

    public String doPost(String url, Map<String, String> map, String charset) {
        CloseableHttpClient httpClient;
        HttpPost httpPost = null;
        String result = null;
        try {
            httpClient = new SSLClient();
            httpPost = new HttpPost(url);
            RequestConfig requestConfig = RequestConfig.custom().setStaleConnectionCheckEnabled(true)
                    .setRedirectsEnabled(true).setMaxRedirects(50).setRelativeRedirectsAllowed(true).setAuthenticationEnabled(true).setConnectTimeout(1500).build();//设置请求超时时间
            httpPost.setConfig(requestConfig);
            //设置参数
            List<NameValuePair> list = new ArrayList<NameValuePair>();
            Iterator iterator = map.entrySet().iterator();
            while (iterator.hasNext()) {
                Entry<String, String> elem = (Entry<String, String>) iterator.next();
                list.add(new BasicNameValuePair(elem.getKey(), elem.getValue()));
            }
            if (list.size() > 0) {
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list, charset);
                httpPost.setEntity(entity);
            }
            HttpResponse response = httpClient.execute(httpPost);
            if (response != null) {
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    result = EntityUtils.toString(resEntity, charset);
                }
            }
        } catch (Exception ex) {
            LOGGER.error("http post error:", ex);
        }
        return result;
    }

    public String doPost(String url, String jsonParam, String charset) {
        CloseableHttpClient httpClient;
        HttpPost httpPost;
        String result = null;
        try {
            httpClient = HttpClients.createDefault();
            httpPost = new HttpPost(url);
            RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(1500).build();//设置请求超时时间
            httpPost.setConfig(requestConfig);
//            httpPost.addHeader("content-type", MediaType.APPLICATION_JSON_VALUE);
            //设置参数
            StringEntity entityRequest = new StringEntity(jsonParam, charset);// 解决中文乱码问题
            httpPost.setEntity(entityRequest);
            CloseableHttpResponse response = httpClient.execute(httpPost);
            if (response != null) {
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    result = EntityUtils.toString(resEntity, charset);
                }
            }
        } catch (Exception ex) {
            LOGGER.error("http post error:", ex);
        }
        return result;
    }

    public String doPut(String url, String jsonParam, String charset) {
        CloseableHttpClient httpClient;
        HttpPut httpPut;
        String result = null;
        try {
            httpClient = HttpClients.createDefault();
            httpPut = new HttpPut(url);
            RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(1500).build();//设置请求超时时间
            httpPut.setConfig(requestConfig);
//            httpPost.addHeader("content-type", MediaType.APPLICATION_JSON_VALUE);
            //设置参数
            StringEntity entityRequest = new StringEntity(jsonParam, charset);// 解决中文乱码问题
            httpPut.setEntity(entityRequest);
            CloseableHttpResponse response = httpClient.execute(httpPut);
            if (response != null) {
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    result = EntityUtils.toString(resEntity, charset);
                }
            }
        } catch (Exception ex) {
            LOGGER.error("http put error:", ex);
        }
        return result;
    }

    public String doGet(String url, String charset) {
        CloseableHttpClient httpClient = null;
        HttpGet httpGet = null;
        String result = null;
        try {
            httpClient = HttpClients.createDefault();
            httpGet = new HttpGet(url);
            RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(1500).build();//设置请求超时时间
            httpGet.setConfig(requestConfig);
            CloseableHttpResponse response = httpClient.execute(httpGet);
            if (response != null) {
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    result = EntityUtils.toString(resEntity, charset);
                }
            }
        } catch (Exception ex) {
            LOGGER.error("http get error:", ex);
        }
        return result;

    }

    public String doGet(String url, HashMap<String, String> paramMap, String charset) {
        CloseableHttpClient httpClient = null;
        HttpGet httpGet = null;
        String result = null;
        try {
            httpClient = HttpClients.createDefault();
            URIBuilder uriBuilder = new URIBuilder(url);
            Iterator iterator = paramMap.entrySet().iterator();
            while (iterator.hasNext()) {
                Entry<String, String> elem = (Entry<String, String>) iterator.next();
                uriBuilder.setParameter(elem.getKey(), elem.getValue());
            }
            URI uri = uriBuilder.build();
            httpGet = new HttpGet(uri);
            RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(1500).build();//设置请求超时时间
            httpGet.setConfig(requestConfig);
            CloseableHttpResponse response = httpClient.execute(httpGet);
            if (response != null) {
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    result = EntityUtils.toString(resEntity, charset);
                }
            }
        } catch (Exception ex) {
            LOGGER.error("http get error:", ex);
        }
        return result;
    }

    public String doDelete(String url, String charset) {
        CloseableHttpClient httpClient = null;
        HttpDelete httpDelete = null;
        String result = null;
        try {
            httpClient = HttpClients.createDefault();
            httpDelete = new HttpDelete(url);
            RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(1500).build();//设置请求超时时间
            httpDelete.setConfig(requestConfig);
            CloseableHttpResponse response = httpClient.execute(httpDelete);
            if (response != null) {
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    result = EntityUtils.toString(resEntity, charset);
                }
            }
        } catch (Exception ex) {
            LOGGER.error("http delete error:", ex);
        }
        return result;
    }
}
