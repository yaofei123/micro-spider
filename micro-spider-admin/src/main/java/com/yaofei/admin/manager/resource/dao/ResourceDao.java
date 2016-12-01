package com.yaofei.admin.manager.resource.dao;


import com.yaofei.admin.manager.resource.domain.Resource;
import com.yaofei.admin.manager.resource.vo.ResourceCondition;
import com.yaofei.framework.util.MybatisMapper;
import com.yaofei.framework.util.Pagination;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * Created by gonghongrui.
 */
@Mapper
public interface ResourceDao extends MybatisMapper {
    void insert(Resource resource);

    void delete(Resource resource);

    void delete(String id);

    List<Resource> findAll(ResourceCondition resourceCondition);

    Long count(ResourceCondition resourceCondition);

    Resource findOne(String id);

    List<Resource> listPageResource(@Param("pagination") Pagination pagination,ResourceCondition resourceCondition);

}
