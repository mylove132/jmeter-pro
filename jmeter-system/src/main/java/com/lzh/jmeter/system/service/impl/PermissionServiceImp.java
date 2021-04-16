package com.lzh.jmeter.system.service.impl;

import com.lzh.jmeter.system.domain.Permission;
import com.lzh.jmeter.system.mapper.PermissionMapper;
import com.lzh.jmeter.system.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionServiceImp implements IPermissionService {

    private final PermissionMapper permissionMapper;

    public PermissionServiceImp(PermissionMapper permissionMapper) {
        this.permissionMapper = permissionMapper;
    }

    @Override
    public List<Permission> getRolePermissions(Integer roleId) {
        return permissionMapper.getRolePermissions(roleId);
    }
}
