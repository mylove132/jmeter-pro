package com.lzh.jmeter.security.config;

import com.alibaba.fastjson.JSON;
import com.lzh.jmeter.commons.core.domain.ResponseData;
import com.lzh.jmeter.commons.core.domain.ResponseUtil;
import com.lzh.jmeter.commons.core.exception.CommonExceptionEnumInterface;
import com.lzh.jmeter.commons.core.utils.JwtTokenUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class JmeterAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        ResponseUtil responseUtil = new ResponseUtil();
        JmeterUserDetail userDetails = (JmeterUserDetail) authentication.getPrincipal();
        String jwtToken = JwtTokenUtils.generateToken(userDetails.getUsername(), 300, "_secret");
        Map<String, String> maps = new HashMap<>();
        maps.put("token", jwtToken);
        ResponseData responseData = responseUtil.success(maps);
        httpServletResponse.getWriter().write(JSON.toJSONString(responseData));
    }
}
