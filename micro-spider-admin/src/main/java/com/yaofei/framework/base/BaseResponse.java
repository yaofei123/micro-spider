package com.yaofei.framework.base;

/**
 * 接口response实体
 * Created by fei.yao on 16/5/26.
 */
public class BaseResponse<T> {
    private int status;
    private T data; //泛型对象,根据不同业务获取不同类型实体
    private String msg;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
