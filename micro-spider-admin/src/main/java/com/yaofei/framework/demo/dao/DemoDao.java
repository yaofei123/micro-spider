package com.yaofei.framework.demo.dao;

import com.yaofei.framework.demo.entity.DemoEntity;
import com.yaofei.framework.util.MybatisMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by fei.yao on 16/8/5.
 */
@Mapper
public interface DemoDao extends MybatisMapper {

    DemoEntity selectDemoEntityById(String id);

}
