package com.yaofei.admin.manager.resource.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yaofei.admin.manager.resource.domain.Resource;
import com.yaofei.admin.manager.resource.service.ResourceService;
import com.yaofei.admin.manager.resource.vo.ResourceCondition;
import com.yaofei.framework.util.PageContent;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

/**
 * 管理资源
 */
@RestController
@RequestMapping(value = "/security/resource")
public class ResourceController {

    private Logger logger = LoggerFactory.getLogger(ResourceController.class);

    @Autowired
    private ResourceService resourceService;


    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Resource add(@RequestBody Resource resource) {

        resourceService.add(resource);

        return resource;
    }

    @RequestMapping(value = "/{idArray}", method = RequestMethod.DELETE)
    public void delete(@PathVariable String idArray) {

        String[] ids = idArray.split(",");

        for (String id : ids) {
            resourceService.delete(id);
        }

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public
    @ResponseBody
    Resource update(
            @RequestBody Resource resource, @PathVariable String id) {
        resource.setId(id);
        resourceService.merge(resource);

        return resource;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Resource get(
            @PathVariable String id
    ) {

        Resource resource = resourceService.find(id);

        return resource;
    }

//	@RequestMapping("/list")
//	public PageContent<Resource> list(
//			@RequestParam(required=false, value="page", defaultValue="0") Integer page,
//			@RequestParam(required=false, value="limit", defaultValue="25") Integer limit,
//			@RequestParam(required=false, value="condition") String condition ){
//
//		ObjectMapper objectMapper = new ObjectMapper();
//
//		ResourceCondition searchCondition = null;
//		if(StringUtils.isNotEmpty(condition)){
//			try {
//				searchCondition = objectMapper.readValue(condition, ResourceCondition.class);
//			} catch (IOException e) {
//				logger.error("convert searchCondition error,condition:("+condition+")", e);
//			}
//		}
//
//		PageRequest pageRequest = new PageRequest(page, limit);
//
//		Page<Resource> resourcePage = resourceService.find(pageRequest, searchCondition);
//		PageContent<Resource> pageContent = new PageContent<>(resourcePage.getContent(), resourcePage.getTotalElements());
//
//		return pageContent;
//
//	}


    @RequestMapping("/tree")
    public PageContent<Resource> tree(@RequestParam(required = false, value = "condition") String condition) {
        ObjectMapper objectMapper = new ObjectMapper();
        ResourceCondition searchCondition = null;
        if (StringUtils.isNotEmpty(condition)) {
            try {
                searchCondition = objectMapper.readValue(condition, ResourceCondition.class);
            } catch (IOException e) {
                logger.error("convert searchCondition error,condition:(" + condition + ")", e);
            }
        }

        List<Resource> resourcePage = resourceService.findResourceTree(searchCondition);
        PageContent<Resource> pageContent = new PageContent<>(resourcePage, resourcePage.size());

        return pageContent;

    }
}
