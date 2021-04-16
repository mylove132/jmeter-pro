package com.lzh.jmeter.system.service.impl;

import com.lzh.jmeter.system.domain.Role;
import com.lzh.jmeter.system.mapper.RoleMapper;
import com.lzh.jmeter.system.service.IRoleService;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class RoleServiceImp implements IRoleService {

    private final RoleMapper roleMapper;

    public RoleServiceImp(RoleMapper roleMapper) {
        this.roleMapper = roleMapper;
    }

    @Override
    public List<Role> getUserRoles(Integer userId) {
        return roleMapper.getUserRoles(userId);
    }

}
