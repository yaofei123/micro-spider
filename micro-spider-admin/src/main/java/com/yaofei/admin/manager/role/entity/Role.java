package com.yaofei.admin.manager.role.entity;

/**
 * 角色
 * Created by fei.yao on 2016/10/19.
 */
public class Role {
    private String id;

    private String code;

    private String name;

    private String parent_code;

    private Boolean available;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParent_code() {
        return parent_code;
    }

    public void setParent_code(String parent_code) {
        this.parent_code = parent_code;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }
}
