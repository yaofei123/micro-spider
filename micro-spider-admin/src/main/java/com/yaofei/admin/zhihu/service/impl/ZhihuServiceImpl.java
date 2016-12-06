package com.yaofei.admin.zhihu.service.impl;

import com.yaofei.admin.zhihu.service.ZhihuService;
import com.yaofei.admin.zhihu.vo.Question;
import com.yaofei.framework.util.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by fei.yao on 2016/11/30.
 */
@Service
public class ZhihuServiceImpl implements ZhihuService {

    @Autowired
    RestTemplate restTemplate;

    @Override
    public Map zhihuQ(Pagination pagination, Question question) {
        String rows = pagination.getRows()+"";
        String page = pagination.getPage()+"";
        return restTemplate.getForEntity("http://MICRO-SPIDER-ZHIHU/zhihuQ?rows="+rows+"&page="+page+"&question="+question.getQuestion()+"&questionDetail="+question.getQuestionDetail(), HashMap.class).getBody();
    }

    @Override
    public Map zhihuA(Pagination pagination,String linkedQ) {
        String rows = pagination.getRows()+"";
        String page = pagination.getPage()+"";
        return restTemplate.getForEntity("http://MICRO-SPIDER-ZHIHU/zhihuA?rows="+rows+"&page="+page+"&linkedQ="+linkedQ, HashMap.class).getBody();
    }
}
