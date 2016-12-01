package com.yaofei.admin.manager.role.service;

import com.yaofei.admin.manager.role.entity.Role;

import java.util.List;

/**
 * Created by fei.yao on 2016/10/19.
 */
public interface RoleService {
    List<Role> findByUserId(String id);
}
