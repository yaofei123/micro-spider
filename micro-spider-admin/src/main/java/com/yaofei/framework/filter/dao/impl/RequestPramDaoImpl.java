package com.yaofei.framework.filter.dao.impl;

import com.yaofei.framework.filter.dao.RequestPramDao;
import com.yaofei.framework.filter.entity.RequestPram;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

/**
 * Created by zhc on 2016/8/10 0010.
 */
@Repository
public class RequestPramDaoImpl implements RequestPramDao {


    private final MongoTemplate mongoTemplate;

    @Autowired
    public RequestPramDaoImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void insert(RequestPram requestPram) {
        mongoTemplate.insert(requestPram);
    }
}
