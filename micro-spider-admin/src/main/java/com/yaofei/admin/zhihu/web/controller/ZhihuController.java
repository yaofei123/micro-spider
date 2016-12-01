package com.yaofei.admin.zhihu.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.yaofei.admin.zhihu.service.ZhihuService;
import com.yaofei.framework.util.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by fei.yao on 2016/10/20.
 */
@Controller
@RequestMapping(value = "/zhihu")
public class ZhihuController {

    @Autowired
    ZhihuService zhihuService;

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add() {
//        restTemplate.getForEntity("http://MICRO-SPIDER-ZHIHU/add?a=10&b=20", String.class).getBody();
        return "";
    }

    @RequestMapping(value = "/zhihu", method = RequestMethod.GET)
    public String zhihu() {
//        restTemplate.getForEntity("http://MICRO-SPIDER-ZHIHU/zhihu?rows=10&page=1", HashMap.class).getBody();
        Map result = new HashMap();
        return JSONObject.toJSONString(result);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String getZhihu() {
        return "zhihu/zhihu";
    }

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public String listZhihu(String content) {
        Pagination pagination = new Pagination();
        Map result = zhihuService.zhihu(pagination);
        return JSONObject.toJSONString(result);
    }


}
