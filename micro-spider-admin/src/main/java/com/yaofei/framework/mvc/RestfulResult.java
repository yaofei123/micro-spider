package com.yaofei.framework.mvc;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fei.yao on 2016/10/19.
 */

public class RestfulResult<T> {
    private boolean success;
    private int code;
    private String message;
    private List<T> data;

    public RestfulResult() {
        this.success = true;
        this.code = 0;
        this.message = "";
        this.data = new ArrayList();
    }

    public RestfulResult(T t) {
        this();
        this.addData(t);
    }

    public RestfulResult(List<T> data) {
        this();
        this.addData(data);
    }

    public void addData(T t) {
        this.data.add(t);
    }

    public void addData(List<T> data) {
        this.data.addAll(data);
    }

    public boolean isSuccess() {
        return this.success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<T> getData() {
        return this.data;
    }
}

