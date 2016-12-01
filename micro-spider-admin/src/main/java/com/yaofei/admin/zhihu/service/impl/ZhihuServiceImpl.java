package com.yaofei.admin.zhihu.service.impl;

import com.yaofei.admin.zhihu.service.ZhihuService;
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
    public Map zhihu(Pagination pagination) {
        String rows = pagination.getRows()+"";
        String page = pagination.getPage()+"";
        Map result = restTemplate.getForEntity("http://MICRO-SPIDER-ZHIHU/zhihu?rows="+rows+"&page="+page, HashMap.class).getBody();
        return result;
    }
}
