package com.yaofei.framework.mvc;

/**
 * Created by fei.yao on 2016/12/1.
 */
public class Result<T> {
    private String msg;
    private Integer status;
    private T data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
