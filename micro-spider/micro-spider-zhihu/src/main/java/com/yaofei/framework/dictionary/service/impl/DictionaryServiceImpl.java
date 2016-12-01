package com.yaofei.framework.dictionary.service.impl;

import com.yaofei.framework.dictionary.dao.DictionaryDao;
import com.yaofei.framework.dictionary.entity.Dictionary;
import com.yaofei.framework.dictionary.service.DictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by fei.yao on 16/7/30.
 */
@Service(value = "dictionaryService")
public class DictionaryServiceImpl implements DictionaryService {

    private final DictionaryDao dictionaryDao;

    @Autowired
    public DictionaryServiceImpl(DictionaryDao dictionaryDao) {
        this.dictionaryDao = dictionaryDao;
    }

    @Override
    //@Cacheable(value = "ucenter-points", key = "'ucenter:points:dictionary'", cacheManager = "dayCache")
    public Map<String, Object> dictionary() {
        Map<String, Object> dictionaryMap = new HashMap<>();
        List<Dictionary> dictionaryList = dictionaryDao.findAll();
        for (Dictionary dictionary : dictionaryList) {
            dictionaryMap.put(dictionary.getKey(), dictionary.getValue());
        }
        return dictionaryMap;
    }
}
