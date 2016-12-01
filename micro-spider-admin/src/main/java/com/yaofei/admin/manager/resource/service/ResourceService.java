package com.yaofei.admin.manager.resource.service;

import com.yaofei.admin.manager.resource.domain.Resource;
import com.yaofei.admin.manager.resource.vo.ResourceCondition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ResourceService {
     void add(Resource resource);

     void add(List<Resource> resourceList);

     void delete(Resource resource);

     void delete(String id);

     void delete(List<Resource> resources);

     void merge(Resource resource);

     void merge(List<Resource> resourceList);

     List<Resource> findResourceTree(ResourceCondition resourceCondition);

     List<Resource> findAll(ResourceCondition resourceCondition);

     Page<Resource> find(Pageable pageable);

     Page<Resource> find(Pageable pageable, ResourceCondition resourceCondition);

     long count(ResourceCondition resourceCondition);

     Resource find(String id);

}
