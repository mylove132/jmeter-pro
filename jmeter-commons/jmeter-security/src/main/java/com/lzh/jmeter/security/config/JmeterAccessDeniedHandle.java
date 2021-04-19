package com.lzh.jmeter.security.config;

import com.alibaba.fastjson.JSON;
import com.lzh.jmeter.commons.core.domain.ResponseData;
import com.lzh.jmeter.commons.core.domain.ResponseUtil;
import com.lzh.jmeter.commons.core.exception.CommonExceptionEnumInterface;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JmeterAccessDeniedHandle implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        ResponseUtil responseUtil = new ResponseUtil();
        ResponseData responseData = responseUtil.fail(CommonExceptionEnumInterface.NOT_AUTHORITIES);
        httpServletResponse.getWriter().write(JSON.toJSONString(responseData));
    }
}
