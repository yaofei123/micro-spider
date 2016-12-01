package com.yaofei.framework.filter.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * Created by zhc on 2016/8/10 0010.
 */
@Document(collection = "ucenter_request_pram")
public class RequestPram {
    private String id;
    private  String url;
    private String method;
    private String parameter;
    private Date requestTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Date getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(Date requestTime) {
        this.requestTime = requestTime;
    }

    public RequestPram(String url, String method, String parameter, Date requestTime) {
        this.url = url;
        this.method = method;
        this.parameter = parameter;
        this.requestTime = requestTime;
    }
}
