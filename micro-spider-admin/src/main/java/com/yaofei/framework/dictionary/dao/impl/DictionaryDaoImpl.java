package com.yaofei.framework.dictionary.dao.impl;

import com.yaofei.framework.dictionary.dao.DictionaryDao;
import com.yaofei.framework.dictionary.entity.Dictionary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by fei.yao on 16/7/30.
 */
@Repository
public class DictionaryDaoImpl implements DictionaryDao {
    private final String collectionName= "ucenter_dictionary";

    private final MongoTemplate mongoTemplate;

    @Autowired
    public DictionaryDaoImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public List<Dictionary> findAll() {
        return mongoTemplate.findAll(Dictionary.class,collectionName);
    }
}
