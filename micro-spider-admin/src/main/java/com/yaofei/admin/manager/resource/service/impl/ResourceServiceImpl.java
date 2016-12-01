package com.yaofei.admin.manager.resource.service.impl;

import com.yaofei.admin.manager.resource.dao.ResourceDao;
import com.yaofei.admin.manager.resource.domain.Resource;
import com.yaofei.admin.manager.resource.service.ResourceService;
import com.yaofei.admin.manager.resource.vo.ResourceCondition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service("resourceService")
public class ResourceServiceImpl implements ResourceService {

    private static final Logger LOGGER = LoggerFactory.getLogger(Resource.class);

    @Autowired
    @Qualifier("resourceDao")
    private ResourceDao resourceDao;

    @Transactional
    public void add(Resource resource) {
        //resource.setId(IDGenerator.get());
        resourceDao.insert(resource);
    }

    @Transactional
    public void add(List<Resource> resourceList) {
        for (Resource resource : resourceList) {
            //resource.setId(IDGenerator.get());
            resourceDao.insert(resource);
        }
    }

    @Transactional
    public void delete(Resource resource) {
        resourceDao.delete(resource);
    }

    @Transactional
    public void delete(String id) {
        resourceDao.delete(id);
    }

    @Transactional
    public void delete(List<Resource> resources) {
        for (Resource resource : resources)
            delete(resource);
    }

    @Transactional
    public void merge(Resource resource) {
        resourceDao.insert(resource);
    }

    @Transactional
    public void merge(List<Resource> resourceList) {
        for (Resource resource : resourceList) {
            resourceDao.insert(resource);
        }
    }

    @Transactional(readOnly = true)
    public List<Resource> findResourceTree(ResourceCondition resourceCondition) {
        List<Resource> resourceList = resourceDao.findAll(resourceCondition);
        HashMap<String, Resource> resourceHashMap = new HashMap<>();
        List<Resource> result = new ArrayList<>();

        for (Resource resource : resourceList) {
            //将查询到的资源整理成map
            resourceHashMap.put(resource.getId(), resource);
            //将根目录放入结果集
            if ("-1".equals(resource.getParentId())) {
                result.add(resource);
            }
        }

        //开始拼装资源树
        String tempParentId;
        Resource tempParentResource;
        for (Resource resource : resourceList) {

            tempParentId = resource.getParentId();

            //如果是顶级节点, 则处理下一个
            if ("-1".equals(resource.getParentId())) {
                continue;
            }

            tempParentResource = resourceHashMap.get(tempParentId);
            if (tempParentResource != null) {
                tempParentResource.addChild(resource);
            }

        }

        return resourceList;
    }

    @Transactional(readOnly = true)
    public List<Resource> findAll(ResourceCondition resourceCondition) {
        List<Resource> resourceList = resourceDao.findAll(resourceCondition);

        return resourceList;
    }

    @Transactional(readOnly = true)
    public Page<Resource> find(Pageable pageable) {
//        Page<Resource> resourceList = resourceDao.findAll(pageable, null);
        return null;
    }

    @Transactional(readOnly = true)
    public Page<Resource> find(Pageable pageable, ResourceCondition resourceCondition) {
        Sort sort = new Sort(Sort.Direction.ASC, "parentId", "orderIndex");
//        Page<Resource> resourceList = resourceDao.findAll(pageable, resourceCondition, sort);
        return null;
    }


    @Transactional(readOnly = true)
    public long count(ResourceCondition resourceCondition) {
        return resourceDao.count(resourceCondition);
    }

    @Transactional(readOnly = true)
    public Resource find(String id) {
        return resourceDao.findOne(id);
    }

}
