package com.lzh.jmeter.security.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lzh.jmeter.commons.core.constant.CacheConstants;
import com.lzh.jmeter.commons.core.domain.ResponseData;
import com.lzh.jmeter.commons.core.domain.ResponseUtil;
import com.lzh.jmeter.commons.core.utils.bean.CommonBeanUtils;
import com.lzh.jmeter.commons.redis.service.RedisService;
import com.lzh.jmeter.security.service.TokenService;
import com.lzh.jmeter.system.api.domain.SysRole;
import com.lzh.jmeter.system.api.model.LoginUser;
import com.lzh.jmeter.system.api.service.ISysUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class JmeterAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final TokenService tokenService;

    @DubboReference
    private ISysUserService sysUserService;

    private final RedisService redisService;

    public JmeterAuthenticationSuccessHandler(TokenService tokenService, RedisService redisService) {
        this.tokenService = tokenService;
        this.redisService = redisService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        ResponseUtil responseUtil = new ResponseUtil();
        JmeterUserDetail userDetails = (JmeterUserDetail) authentication.getPrincipal();
        SecurityContextHolder.getContext().setAuthentication(authentication);
        log.info("jmeter userdetails: {}", userDetails);
        ResponseData<LoginUser> loginUserResponseData = sysUserService.selectSysUserByName(userDetails.getUsername());
        // redis中获取token
        String token = null;
        String tokenJson = redisService.get(CacheConstants.LOGIN_TOKEN_KEY + userDetails.getUsername()).toString();
        if (null != tokenJson && !"".equals(tokenJson)) {
            Map<String, String> tokenMap = JSONObject.parseObject(tokenJson, Map.class);
            token = tokenMap.get("token");
        }
        //
        Map<String, Object> tokenResult = new HashMap<>();
        if (null == token) {
            tokenResult = tokenService.createToken(loginUserResponseData.getData());
        }
        // 设置用户角色菜单权限
        ResponseData<SysRole> role = sysUserService.selectSysRoleByRoleId(loginUserResponseData.getData().getUserid());
        tokenResult.put("menuIds",  role.getData().getMenuIds());
        ResponseData responseData = responseUtil.success(tokenResult);
        httpServletResponse.getWriter().write(JSON.toJSONString(responseData));
    }
}
