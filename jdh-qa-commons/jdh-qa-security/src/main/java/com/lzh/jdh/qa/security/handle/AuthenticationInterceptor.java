package com.lzh.jdh.qa.security.handle;
import com.alibaba.fastjson.JSON;
import com.lzh.jdh.qa.commons.core.constant.CacheConstants;
import com.lzh.jdh.qa.commons.core.constant.Constants;
import com.lzh.jdh.qa.commons.core.domain.ResponseData;
import com.lzh.jdh.qa.commons.core.domain.ResponseUtil;
import com.lzh.jdh.qa.commons.core.exception.CommonExceptionEnumInterface;
import com.lzh.jdh.qa.commons.core.utils.StringUtils;
import com.lzh.jdh.qa.commons.redis.service.RedisService;
import com.lzh.jdh.qa.security.service.TokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class AuthenticationInterceptor implements HandlerInterceptor {

    private final RedisService redisService;

    private final TokenService tokenService;

    public AuthenticationInterceptor(RedisService redisService, TokenService tokenService) {
        this.redisService = redisService;
        this.tokenService = tokenService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader(CacheConstants.AUTHORIZATION_HEADER);
        ResponseUtil responseUtil = new ResponseUtil();

        // 如果请求头没有认证
        if (StringUtils.isBlank(token)) {
            ResponseData responseData = responseUtil.fail(CommonExceptionEnumInterface.NOT_AUTHORITIES);
            response.getWriter().write(JSON.toJSONString(responseData));
            return false;
        }
        // 通过token获取获取用户名
        String username = tokenService.getUsername(token);
        if (StringUtils.isBlank(username)) {
            ResponseData responseData = responseUtil.fail(CommonExceptionEnumInterface.AUTHORITIES_FAIL);
            response.getWriter().write(JSON.toJSONString(responseData));
            return false;
        }
        // redis不存在缓存时 || 缓存过期
        if (!redisService.hasKey(CacheConstants.TOKEN_PREFIX + username) || tokenService.isExpiration(CacheConstants.TOKEN_PREFIX)){
            ResponseData responseData = responseUtil.fail(CommonExceptionEnumInterface.AUTHORITIES_EXPIRED);
            response.getWriter().write(JSON.toJSONString(responseData));
            return false;
        }
        // 更新缓存时长
        redisService.expire(CacheConstants.TOKEN_PREFIX + username, Constants.TOKEN_EXPIRE);
        return true;
    }
}
