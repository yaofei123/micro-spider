package com.yaofei.framework.dictionary.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * Created by fei.yao on 16/7/30.
 */
@Document(collection = "ucenter_dictionary")
public class Dictionary implements Serializable {

    /**
     * 常量名
     */
    private String key;

    /**
     * 常量值
     */
    private String value;

    /**
     * 描述
     */
    private String description;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
