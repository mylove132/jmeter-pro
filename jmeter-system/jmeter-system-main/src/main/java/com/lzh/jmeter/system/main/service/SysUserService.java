package com.lzh.jmeter.system.main.service;

import com.lzh.jmeter.commons.core.domain.ResponseData;
import com.lzh.jmeter.commons.core.domain.ResponseUtil;
import com.lzh.jmeter.commons.core.exception.BizException;
import com.lzh.jmeter.system.api.domain.SysRole;
import com.lzh.jmeter.system.api.domain.SysUser;
import com.lzh.jmeter.system.api.model.LoginUser;
import com.lzh.jmeter.system.api.service.ISysPermissionService;
import com.lzh.jmeter.system.api.service.ISysUserService;
import com.lzh.jmeter.system.main.mapper.SysRoleMapper;
import com.lzh.jmeter.system.main.mapper.SysUserMapper;
import org.apache.dubbo.config.annotation.DubboService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@DubboService(interfaceClass = ISysUserService.class)
public class SysUserService implements ISysUserService {

    private final SysUserMapper sysUserMapper;

    private final SysRoleMapper sysRoleMapper;

    private final ISysPermissionService sysPermissionService;

    public SysUserService(SysUserMapper sysUserMapper, SysRoleMapper sysRoleMapper, ISysPermissionService sysPermissionService) {
        this.sysUserMapper = sysUserMapper;
        this.sysRoleMapper = sysRoleMapper;
        this.sysPermissionService = sysPermissionService;
    }

    @Override
    public ResponseData<LoginUser> selectSysUserByName(String userName) throws BizException {
        SysUser sysUser = sysUserMapper.selectUserByname(userName);
        LoginUser loginUser = new LoginUser();
        loginUser.setSysUser(sysUser);
        loginUser.setUsername(userName);

        List<SysRole> roles = sysRoleMapper.selectRolePermissionByUserId(sysUser.getUserId());
        Set<String> roleNames = new HashSet<>();
        new HashSet<SysRole>(roles).forEach(
                sysRole -> {
                    roleNames.add(sysRole.getRoleName());
                }
        );
        loginUser.setRoles(roleNames);
        loginUser.setPermissions(sysPermissionService.getMenuPermission(sysUser.getUserId()).getData());
        return new ResponseUtil().success(loginUser);
    }

    @Override
    public ResponseData<SysRole> selectSysRoleByRoleId(Long roleId) throws BizException {
        return null;
    }
}
