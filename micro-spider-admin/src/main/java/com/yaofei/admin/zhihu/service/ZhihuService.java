package com.yaofei.admin.zhihu.service;

import com.yaofei.admin.zhihu.vo.Question;
import com.yaofei.framework.util.Pagination;

import java.util.Map;

/**
 * Created by fei.yao on 2016/11/30.
 */
public interface ZhihuService {
    Map zhihuQ(Pagination pagination, Question question);


    Map zhihuA(Pagination pagination,String linkedQ);
}
