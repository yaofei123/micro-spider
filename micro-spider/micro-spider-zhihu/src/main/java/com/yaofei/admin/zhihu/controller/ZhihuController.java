package com.yaofei.admin.zhihu.controller;

import com.yaofei.admin.zhihu.entity.ZhihuData;
import com.yaofei.admin.zhihu.service.ZhihuService;
import com.yaofei.framework.mvc.CoreBaseResult;
import com.yaofei.framework.util.Pagination;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.QueryParam;
import java.util.List;
import java.util.Map;

/**
 * Created by fei.yao on 2016/10/18.
 */
@RestController
public class ZhihuController extends CoreBaseResult {

    private static final Logger LOGGER = LoggerFactory.getLogger(ZhihuController.class);

    @Autowired
    DiscoveryClient client;

    @Autowired
    ZhihuService zhihuService;

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public Integer add(@RequestParam Integer a, @RequestParam Integer b) {
        ServiceInstance instance = client.getLocalServiceInstance();
        Integer r = a + b;
        LOGGER.info("/add, host:" + instance.getHost() + ", service_id:" + instance.getServiceId() + ", result:" + r);
        return r;
    }

    @RequestMapping(value = "/zhihu", method = RequestMethod.GET)
    public Map<String, Object> getZhihuData(HttpServletRequest request,
                                            @QueryParam("rows") @DefaultValue("20") Integer rows,
                                            @QueryParam("page") @DefaultValue("1") Integer page) {
        Pagination pagination = new Pagination();
        pagination.setPage(page);
        pagination.setRows(rows);
        try {
            List<ZhihuData> zhihuDataList = zhihuService.selectZhihuData(pagination);
            return success(zhihuDataList);
        } catch (Exception e) {
            LOGGER.error("",e);
            return error(e);
        }
    }
}
