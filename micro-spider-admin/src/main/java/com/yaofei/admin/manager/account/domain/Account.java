package com.yaofei.admin.manager.account.domain;

/**
 * admin用户表
 * Created by fei.yao on 2016/10/19.
 */
public class Account {
    private String id;
    private String userName;
    private String password;
    private String roleCodes;
    private String available;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoleCodes() {
        return roleCodes;
    }

    public void setRoleCodes(String roleCodes) {
        this.roleCodes = roleCodes;
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }
}
