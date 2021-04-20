package com.lzh.jmeter.system.main.mapper;

import com.lzh.jmeter.system.api.domain.SysRole;

import java.util.List;

public interface SysRoleMapper {

    public List<SysRole> selectRolePermissionByUserId(Long userId);
}
