package com.lzh.jmeter.system.main.service;
import com.lzh.jmeter.commons.core.domain.ResponseData;
import com.lzh.jmeter.system.api.domain.SysRole;
import com.lzh.jmeter.system.api.service.ISysRoleService;
import com.lzh.jmeter.system.main.mapper.SysRoleMapper;
import org.apache.dubbo.config.annotation.DubboService;

import java.util.*;

/**
 * 角色 业务层处理
 *
 */
@DubboService(interfaceClass = ISysRoleService.class)
public class SysRoleService implements ISysRoleService
{
    private final SysRoleMapper roleMapper;

    public SysRoleService(SysRoleMapper roleMapper) {
        this.roleMapper = roleMapper;
    }


    @Override
    public ResponseData<List<SysRole>> selectRoleList(SysRole role) {
        return null;
    }

    @Override
    public ResponseData<Set<String>> selectRolePermissionByUserId(Long userId) {
        return null;
    }

    @Override
    public ResponseData<List<SysRole>> selectRoleAll() {
        return null;
    }

    @Override
    public ResponseData<List<Integer>> selectRoleListByUserId(Long userId) {
        return null;
    }

    @Override
    public ResponseData<SysRole> selectRoleById(Long roleId) {
        return null;
    }
}
