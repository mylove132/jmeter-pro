package com.lzh.jdh.qa.system.api.service;

import com.lzh.jdh.qa.commons.core.domain.ResponseData;
import com.lzh.jdh.qa.system.api.domain.SysMenu;

import java.util.List;
import java.util.Set;

public interface ISysMenuService {

    /**
     * 根据用户查询系统菜单列表
     *
     * @param userId 用户ID
     * @return 菜单列表
     */
    ResponseData<List<SysMenu>> selectMenuList(Long userId);

    /**
     * 根据用户查询系统菜单列表
     *
     * @param menu 菜单信息
     * @param userId 用户ID
     * @return 菜单列表
     */
    ResponseData<List<SysMenu>> selectMenuList(SysMenu menu, Long userId);

    /**
     * 根据用户ID查询权限
     *
     * @param userId 用户ID
     * @return 权限列表
     */
    ResponseData<Set<String>> selectMenuPermsByUserId(Long userId);

    /**
     * 根据用户ID查询菜单树信息
     *
     * @param userId 用户ID
     * @return 菜单列表
     */ResponseData<List<SysMenu>> selectMenuTreeByUserId(Long userId);

    /**
     * 根据角色ID查询菜单树信息
     *
     * @param roleId 角色ID
     * @return 选中菜单列表
     */
    ResponseData<List<Integer>> selectMenuListByRoleId(Long roleId);
}
