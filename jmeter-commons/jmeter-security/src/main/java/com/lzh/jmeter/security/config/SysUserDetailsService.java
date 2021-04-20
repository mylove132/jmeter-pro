package com.lzh.jmeter.security.config;

import com.lzh.jmeter.commons.core.domain.ResponseData;
import com.lzh.jmeter.system.api.domain.SysRole;
import com.lzh.jmeter.system.api.domain.SysUser;
import com.lzh.jmeter.system.api.model.LoginUser;
import com.lzh.jmeter.system.api.service.ISysUserService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SysUserDetailsService implements UserDetailsService {

    @DubboReference
    private ISysUserService sysUserService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        ResponseData<LoginUser> responseData = sysUserService.selectSysUserByName(s);
        SysUser user = responseData.getData().getSysUser();
        JmeterUserDetail userDetail = new JmeterUserDetail();
        userDetail.setUsername(user.getUserName());
        userDetail.setPassword(user.getPassword());

        Set authoritiesSet = new HashSet();

        List<SysRole> roles =  user.getRoles();
        roles.forEach(
                sysRole -> {
                    GrantedAuthority authority = new SimpleGrantedAuthority(sysRole.getRoleName());
                    authoritiesSet.add(authority);
                }
        );

        userDetail.setAuthorities(authoritiesSet);
       // 模拟从数据库中获取用户角色
        return userDetail;
    }
}
