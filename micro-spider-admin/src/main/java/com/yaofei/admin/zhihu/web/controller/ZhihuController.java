package com.yaofei.admin.zhihu.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.yaofei.admin.zhihu.service.ZhihuService;
import com.yaofei.admin.zhihu.vo.Answer;
import com.yaofei.admin.zhihu.vo.Question;
import com.yaofei.admin.zhihu.vo.ZhihuResult;
import com.yaofei.framework.mvc.PageSearchRequest;
import com.yaofei.framework.mvc.Result;
import com.yaofei.framework.util.PageContent;
import com.yaofei.framework.util.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by fei.yao on 2016/10/20.
 */
@Controller
@RequestMapping(value = "/zhihu")
public class ZhihuController {

    static final Integer SUCCESS = 0; // 成功

    @Autowired
    ZhihuService zhihuService;

    @RequestMapping(value = "/listQ", method = RequestMethod.POST)
    @ResponseBody
    public PageContent<Question> listZhihuQ(@RequestBody PageSearchRequest<Question> pageSearchRequest) {
        PageContent<Question>  pageContent = new PageContent<>();
        Pagination pagination = new Pagination();
        pagination.setPage(pageSearchRequest.getPage());
        pagination.setRows(pageSearchRequest.getLimit());
        Map resultMap = zhihuService.zhihuQ(pagination,pageSearchRequest.getSearchCondition() ==null ? new Question():pageSearchRequest.getSearchCondition());
        Result<ZhihuResult<List<Question>>> result = JSONObject.parseObject(JSONObject.toJSONString(resultMap), new TypeReference<Result<ZhihuResult<List<Question>>>>() {
        });
        if (SUCCESS.equals(result.getStatus())) {
            ZhihuResult<List<Question>> zhihuResult = result.getData();
            pagination = zhihuResult.getPagination();
            pageContent.setTotal(pagination.getTotal());
            pageContent.setContent(zhihuResult.getResultData());
        }
        return pageContent;
    }

    @RequestMapping(value = "/listA/{linkQ}", method = RequestMethod.POST)
    @ResponseBody
    public PageContent<Answer> listZhihuA(String content, @PathVariable("linkQ") String linkQ) {
        PageContent<Answer>  pageContent = new PageContent<>();
        Pagination pagination = new Pagination();
        Map resultMap = zhihuService.zhihuA(pagination,linkQ);
        Result<ZhihuResult<List<Answer>>> result = JSONObject.parseObject(JSONObject.toJSONString(resultMap), new TypeReference<Result<ZhihuResult<List<Answer>>>>() {
        });
        if (SUCCESS.equals(result.getStatus())) {
            ZhihuResult<List<Answer>> zhihuResult = result.getData();
            pagination = zhihuResult.getPagination();
            pageContent.setTotal(pagination.getTotal());
            pageContent.setContent(zhihuResult.getResultData());
        }
        return pageContent;
    }


}
