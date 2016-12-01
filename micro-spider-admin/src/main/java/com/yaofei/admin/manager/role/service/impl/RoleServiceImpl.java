package com.yaofei.admin.manager.role.service.impl;

import com.yaofei.admin.manager.role.entity.Role;
import com.yaofei.admin.manager.role.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by fei.yao on 2016/10/19.
 */
@Service(value = "roleService")
public class RoleServiceImpl implements RoleService {
    @Override
    public List<Role> findByUserId(String id) {
        return null;
    }
}
