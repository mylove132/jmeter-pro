package com.lzh.jdh.qa.system.main.service;

import com.lzh.jdh.qa.commons.core.domain.ResponseData;
import com.lzh.jdh.qa.commons.core.domain.ResponseUtil;
import com.lzh.jdh.qa.commons.core.exception.BizException;
import com.lzh.jdh.qa.system.api.domain.SysRole;
import com.lzh.jdh.qa.system.api.domain.SysUser;
import com.lzh.jdh.qa.system.api.model.LoginUser;
import com.lzh.jdh.qa.system.api.service.ISysPermissionService;
import com.lzh.jdh.qa.system.api.service.ISysUserService;
import com.lzh.jdh.qa.system.main.mapper.SysRoleMapper;
import com.lzh.jdh.qa.system.main.mapper.SysUserMapper;
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
        System.out.println(sysUser);
        if (sysUser == null) {
            throw new BizException(28888, "用户名不存在");
        }
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
