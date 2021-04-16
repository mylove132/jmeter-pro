package com.lzh.jmeter.system.service;

import com.lzh.jmeter.system.domain.Permission;

import java.util.List;

public interface IPermissionService {
    List<Permission> getRolePermissions(Integer roleId);
}
