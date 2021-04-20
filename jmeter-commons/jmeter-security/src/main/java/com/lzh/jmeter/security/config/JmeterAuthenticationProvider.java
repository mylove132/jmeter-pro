package com.lzh.jmeter.security.config;

import com.lzh.jmeter.commons.core.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

public class JmeterAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    SysUserDetailsService userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String userName = (String) authentication.getPrincipal(); // 这个获取表单输入中返回的用户名;
        String password = (String) authentication.getCredentials(); // 这个是表单中输入的密码；
        UserDetails userInfo = userDetailsService.loadUserByUsername(userName);
        if (!SecurityUtils.matchesPassword(password, userInfo.getPassword())) {
            throw new BadCredentialsException("用户名密码不正确，请重新登陆！");
        }
        return new UsernamePasswordAuthenticationToken(userName, password, userInfo.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }
}
