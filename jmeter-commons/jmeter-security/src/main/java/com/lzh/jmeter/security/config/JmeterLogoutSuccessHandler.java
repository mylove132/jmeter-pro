package com.lzh.jmeter.security.config;
import com.lzh.jmeter.commons.core.constant.CacheConstants;
import com.lzh.jmeter.commons.core.utils.StringUtils;
import com.lzh.jmeter.security.service.TokenService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JmeterLogoutSuccessHandler implements LogoutSuccessHandler {


    private final TokenService tokenService;

    public JmeterLogoutSuccessHandler(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        String headerToken = httpServletRequest.getHeader(CacheConstants.AUTHORIZATION_HEADER);
        if (StringUtils.isNotEmpty(headerToken)) {
            String token = headerToken.replace(CacheConstants.LOGIN_TOKEN_KEY, "").trim();
            tokenService.delLoginUser(token);
            SecurityContextHolder.clearContext();
        }
    }
}
