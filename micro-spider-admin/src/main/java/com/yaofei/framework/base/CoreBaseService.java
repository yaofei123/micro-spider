package com.yaofei.framework.base;


import java.util.Map;

/**
 * @author fei.yao
 */
public class CoreBaseService extends CoreBaseResult {

    /**
     * 验证分页参数是否为空
     *
     * @param paginationRequest 分页请求参数
     * @return
     */
    protected Map<String, Object> validPaginationParameter(PaginationRequest paginationRequest) {
        if (paginationRequest == null || paginationRequest.getPageNum() == null || paginationRequest.getPageSize() == null) {
            return invalidParameter();
        }
        return null;
    }
}
