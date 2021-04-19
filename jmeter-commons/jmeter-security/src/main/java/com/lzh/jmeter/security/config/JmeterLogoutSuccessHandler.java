package com.lzh.jmeter.security.config;

import com.alibaba.fastjson.JSON;
import com.lzh.jmeter.commons.core.domain.ResponseData;
import com.lzh.jmeter.commons.core.domain.ResponseUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JmeterLogoutSuccessHandler implements LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        ResponseUtil responseUtil = new ResponseUtil();
        ResponseData responseData = responseUtil.success();
        httpServletResponse.getWriter().write(JSON.toJSONString(responseData));
    }
}
