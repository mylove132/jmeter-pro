package com.lzh.jdh.qa.system.main.mapper;

import com.lzh.jdh.qa.system.api.domain.SysRole;

import java.util.List;

public interface SysRoleMapper {

    public List<SysRole> selectRolePermissionByUserId(Long userId);
}
