package com.lzh.jmeter.security.service;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.lzh.jmeter.commons.core.constant.CacheConstants;
import com.lzh.jmeter.commons.core.constant.Constants;
import com.lzh.jmeter.commons.core.utils.DateUtils;
import com.lzh.jmeter.commons.redis.service.RedisService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
/**
 * token验证处理
 *
 */
@Component
@Slf4j
public class TokenService
{
    private final RedisService redisService;

    protected static final long MILLIS_SECOND = 1000;

    private static final String ISS = "echisan";

    public TokenService(RedisService redisService) {
        this.redisService = redisService;
    }

    // 创建token
    public Map<String, String> createToken(String username) {
        long expiration = Constants.TOKEN_EXPIRE ;
        Date expireTime = new Date(System.currentTimeMillis() + expiration * MILLIS_SECOND);
        String token = Jwts.builder()
                .signWith(SignatureAlgorithm.HS512, CacheConstants.SECRET)
                .setIssuer(ISS)
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expireTime)
                .compact();
        // token放入缓存
        Map<String, String> result = new HashMap<>();
        if (redisService.hasKey(CacheConstants.TOKEN_PREFIX + username)) {
            log.info("缓存key:{}已经存在， 更新缓存", CacheConstants.TOKEN_PREFIX + username);
            redisService.expire(CacheConstants.TOKEN_PREFIX + username, expiration);
        } else {
            redisService.set(CacheConstants.TOKEN_PREFIX + username, token, expiration);
        }
        result.put("token", token);
        result.put("expireTime", DateUtils.dateTime(expireTime));
        return result;
    }

    // 从token中获取用户名
    public String getUsername(String token){
        try {
            return getTokenBody(token).getSubject();
        } catch (Exception e) {
            return "";
        }

    }

    // 是否已过期
    public boolean isExpiration(String token){
        return getTokenBody(token).getExpiration().before(new Date());
    }

    private Claims getTokenBody(String token){
        return Jwts.parser()
                .setSigningKey(CacheConstants.SECRET)
                .parseClaimsJws(token)
                .getBody();
    }
}

