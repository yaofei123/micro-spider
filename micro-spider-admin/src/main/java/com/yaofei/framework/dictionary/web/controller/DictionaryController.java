package com.yaofei.framework.dictionary.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.yaofei.framework.dictionary.service.DictionaryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.Produces;
import java.util.Map;

/**
 * Created by fei.yao on 16/7/30.
 */
@RestController
@RequestMapping("dictionary")
public class DictionaryController {
    private static final Logger LOGGER = LoggerFactory.getLogger(DictionaryController.class);

    private final DictionaryService dictionaryService;

    @Autowired
    public DictionaryController(DictionaryService dictionaryService) {
        this.dictionaryService = dictionaryService;
    }


    @RequestMapping(method = RequestMethod.GET)
    @Produces("application/json;charset=utf-8")
    public ResponseEntity<String> findAllDictionary() {
        LOGGER.info("请求数据字典");
        Map<String, Object> dictionaryList = dictionaryService.dictionary();
        return new ResponseEntity<String>(JSONObject.toJSONString(dictionaryList), HttpStatus.OK);
    }
}
