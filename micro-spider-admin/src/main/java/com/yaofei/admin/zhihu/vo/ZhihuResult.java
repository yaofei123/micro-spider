package com.yaofei.admin.zhihu.vo;

import com.yaofei.framework.util.Pagination;

/**
 * Created by fei.yao on 2016/12/1.
 */
public class ZhihuResult<T> {
    private Pagination pagination;
    private T resultData;

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }

    public T getResultData() {
        return resultData;
    }

    public void setResultData(T resultData) {
        this.resultData = resultData;
    }
}
