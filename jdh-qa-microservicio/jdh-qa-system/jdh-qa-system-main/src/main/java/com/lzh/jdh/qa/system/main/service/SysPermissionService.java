package com.lzh.jdh.qa.system.main.service;

import com.lzh.jdh.qa.commons.core.domain.ResponseData;
import com.lzh.jdh.qa.commons.core.domain.ResponseUtil;
import com.lzh.jdh.qa.system.api.domain.SysUser;
import com.lzh.jdh.qa.system.api.service.ISysMenuService;
import com.lzh.jdh.qa.system.api.service.ISysPermissionService;
import com.lzh.jdh.qa.system.api.service.ISysRoleService;
import org.apache.dubbo.config.annotation.DubboService;


import java.util.HashSet;
import java.util.Set;

@DubboService(interfaceClass = ISysPermissionService.class)
public class SysPermissionService implements ISysPermissionService
{
    private final ISysRoleService roleService;

    private final ISysMenuService menuService;

    public SysPermissionService(ISysMenuService menuService, ISysRoleService roleService) {
        this.menuService = menuService;
        this.roleService = roleService;
    }

    /**
     * 获取角色数据权限
     * 
     * @param userId 用户Id
     * @return 角色权限信息
     */
    @Override
    public ResponseData<Set<String>> getRolePermission(Long userId)
    {
        Set<String> roles = new HashSet<String>();
        // 管理员拥有所有权限
        if (SysUser.isAdmin(userId))
        {
            roles.add("admin");
        }
        else
        {
            roles.addAll(roleService.selectRolePermissionByUserId(userId).getData());
        }
        return new ResponseUtil().success(roles);
    }

    /**
     * 获取菜单数据权限
     * 
     * @param userId 用户Id
     * @return 菜单权限信息
     */
    @Override
    public ResponseData<Set<String>> getMenuPermission(Long userId)
    {
        Set<String> perms = new HashSet<String>();
        // 管理员拥有所有权限
        if (SysUser.isAdmin(userId))
        {
            perms.add("*:*:*");
        }
        else
        {
            perms.addAll(menuService.selectMenuPermsByUserId(userId).getData());
        }
        return new ResponseUtil().success(perms);
    }
}
