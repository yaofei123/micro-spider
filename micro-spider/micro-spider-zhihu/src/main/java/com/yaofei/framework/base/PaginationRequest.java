package com.yaofei.framework.base;

import java.io.Serializable;

/**
 * Created by fei.yao on 16/8/5.
 */
public class PaginationRequest implements Serializable{
    /**
     * 起始页数
     */
    protected Integer pageNum = 1;

    /**
     * 每页条数
     */
    protected Integer pageSize = 20;

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

}
