package com.lzh.jdh.qa.system.api.service;

import com.lzh.jdh.qa.commons.core.domain.ResponseData;

import java.util.Set;

public interface ISysPermissionService {

    ResponseData<Set<String>> getRolePermission(Long userId);

    /**
     * 获取菜单数据权限
     *
     * @param userId 用户Id
     * @return 菜单权限信息
     */
    ResponseData<Set<String>> getMenuPermission(Long userId);
}
