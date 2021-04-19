package com.lzh.jmeter.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

public class JmeterSecurityConfigure extends WebSecurityConfigurerAdapter {

    @Autowired
    JmeterAuthenticationEntryPoint jmeterAuthenticationEntryPoint;  //  未登陆时返回 JSON 格式的数据给前端（否则为 html）

    @Autowired
    JmeterAuthenticationSuccessHandler jmeterAuthenticationSuccessHandler;  // 登录成功返回的 JSON 格式数据给前端（否则为 html）

    @Autowired
    JmeterAuthenticationFailureHandler jmeterAuthenticationFailureHandler;  //  登录失败返回的 JSON 格式数据给前端（否则为 html）

    @Autowired
    JmeterLogoutSuccessHandler  jmeterLogoutSuccessHandler;  // 注销成功返回的 JSON 格式数据给前端（否则为 登录时的 html）

    @Autowired
    JmeterAccessDeniedHandle jmeterAccessDeniedHandle;    // 无权访问返回的 JSON 格式数据给前端（否则为 403 html 页面）

    @Autowired
    JmeterAuthenticationProvider jmeterAuthenticationProvider; // 自定义安全认证

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 加入自定义的安全认证
        auth.authenticationProvider(jmeterAuthenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable()

                .httpBasic().authenticationEntryPoint(jmeterAuthenticationEntryPoint)

                .and()
                .authorizeRequests()

                .anyRequest()
                .authenticated()// 其他 url 需要身份认证

                .and()
                .formLogin()  //开启登录
                .successHandler(jmeterAuthenticationSuccessHandler) // 登录成功
                .failureHandler(jmeterAuthenticationFailureHandler) // 登录失败
                .permitAll()

                .and()
                .logout()
                .logoutSuccessHandler(jmeterLogoutSuccessHandler)
                .permitAll();

        http.exceptionHandling().accessDeniedHandler(jmeterAccessDeniedHandle); // 无权访问 JSON 格式的数据

    }
}