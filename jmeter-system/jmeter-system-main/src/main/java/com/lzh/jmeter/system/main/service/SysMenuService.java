package com.lzh.jmeter.system.main.service;

import com.lzh.jmeter.commons.core.domain.ResponseData;
import com.lzh.jmeter.system.api.domain.SysMenu;
import com.lzh.jmeter.system.api.service.ISysMenuService;
import com.lzh.jmeter.system.main.mapper.SysMenuMapper;
import com.lzh.jmeter.system.main.mapper.SysRoleMapper;
import org.apache.dubbo.config.annotation.DubboService;

import java.util.*;

/**
 * 菜单 业务层处理
 */
@DubboService(interfaceClass = ISysMenuService.class)
public class SysMenuService implements ISysMenuService
{

    private final SysMenuMapper menuMapper;

    private final SysRoleMapper roleMapper;

    public SysMenuService(SysMenuMapper menuMapper, SysRoleMapper roleMapper) {
        this.menuMapper = menuMapper;
        this.roleMapper = roleMapper;
    }

    @Override
    public ResponseData<List<SysMenu>> selectMenuList(Long userId) {
        return null;
    }

    @Override
    public ResponseData<List<SysMenu>> selectMenuList(SysMenu menu, Long userId) {
        return null;
    }

    @Override
    public ResponseData<Set<String>> selectMenuPermsByUserId(Long userId) {
        return null;
    }

    @Override
    public ResponseData<List<SysMenu>> selectMenuTreeByUserId(Long userId) {
        return null;
    }

    @Override
    public ResponseData<List<Integer>> selectMenuListByRoleId(Long roleId) {
        return null;
    }
}
