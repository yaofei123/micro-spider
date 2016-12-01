package com.yaofei.framework.demo.entity;

import java.io.Serializable;

/**
 * 会员积分信用卡账户(负分账户,优先平正)
 * Created by fei.yao on 16/7/26.
 */
public class DemoEntity implements Serializable {
    /**
     * uuid
     */
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
