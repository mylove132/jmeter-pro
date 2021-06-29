package com.lzh.jdh.qa.system.api.service;

import com.lzh.jdh.qa.commons.core.domain.ResponseData;
import com.lzh.jdh.qa.system.api.domain.SysRole;

import java.util.List;
import java.util.Set;

public interface ISysRoleService {

    /**
     * 根据条件分页查询角色数据
     *
     * @param role 角色信息
     * @return 角色数据集合信息
     */
    ResponseData<List<SysRole>> selectRoleList(SysRole role);

    /**
     * 根据用户ID查询角色
     *
     * @param userId 用户ID
     * @return 权限列表
     */
    ResponseData<Set<String>> selectRolePermissionByUserId(Long userId);

    /**
     * 查询所有角色
     *
     * @return 角色列表
     */
    ResponseData<List<SysRole>> selectRoleAll();

    /**
     * 根据用户ID获取角色选择框列表
     *
     * @param userId 用户ID
     * @return 选中角色ID列表
     */
    ResponseData<List<Integer>> selectRoleListByUserId(Long userId);

    /**
     * 通过角色ID查询角色
     *
     * @param roleId 角色ID
     * @return 角色对象信息
     */
    ResponseData<SysRole> selectRoleById(Long roleId);
}
