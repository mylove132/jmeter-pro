package com.lzh.jmeter.security.config;
import com.lzh.jmeter.commons.core.utils.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Slf4j
@Configuration
public class JmeterWebSecurityConfigure extends WebSecurityConfigurerAdapter {

    final JmeterAuthenticationEntryPoint jmeterAuthenticationEntryPoint;  //  未登陆时返回 JSON 格式的数据给前端（否则为 html）

    final JmeterAuthenticationSuccessHandler jmeterAuthenticationSuccessHandler;  // 登录成功返回的 JSON 格式数据给前端（否则为 html）

    final JmeterAuthenticationFailureHandler jmeterAuthenticationFailureHandler;  //  登录失败返回的 JSON 格式数据给前端（否则为 html）

    final JmeterLogoutSuccessHandler jmeterLogoutSuccessHandler;  // 注销成功返回的 JSON 格式数据给前端（否则为 登录时的 html）

    final JmeterAccessDeniedHandle jmeterAccessDeniedHandle;    // 无权访问返回的 JSON 格式数据给前端（否则为 403 html 页面）

    final JmeterAuthenticationProvider jmeterAuthenticationProvider; // 自定义安全认证

    public JmeterWebSecurityConfigure(JmeterAuthenticationEntryPoint jmeterAuthenticationEntryPoint, JmeterAuthenticationSuccessHandler jmeterAuthenticationSuccessHandler, JmeterAuthenticationFailureHandler jmeterAuthenticationFailureHandler, JmeterLogoutSuccessHandler jmeterLogoutSuccessHandler, JmeterAccessDeniedHandle jmeterAccessDeniedHandle, JmeterAuthenticationProvider jmeterAuthenticationProvider) {
        this.jmeterAuthenticationEntryPoint = jmeterAuthenticationEntryPoint;
        this.jmeterAuthenticationSuccessHandler = jmeterAuthenticationSuccessHandler;
        this.jmeterAuthenticationFailureHandler = jmeterAuthenticationFailureHandler;
        this.jmeterLogoutSuccessHandler = jmeterLogoutSuccessHandler;
        this.jmeterAccessDeniedHandle = jmeterAccessDeniedHandle;
        this.jmeterAuthenticationProvider = jmeterAuthenticationProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 加入自定义的安全认证
        auth.authenticationProvider(jmeterAuthenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        log.info("------------------------------:{}", SecurityUtils.encryptPassword("123456"));
        http.csrf().disable()
                .httpBasic().authenticationEntryPoint(jmeterAuthenticationEntryPoint)
                .and()
                .authorizeRequests().antMatchers(
                "/api/register",
                "/api/login"
                ).permitAll()
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