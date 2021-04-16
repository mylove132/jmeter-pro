package com.lzh.jmeter.commons.core.constant;

/**
 * 缓存的key 常量
 *
 */
public class CacheConstants
{
    /**
     * 授权信息字段
     */
    public static final String AUTHORIZATION_HEADER = "authorization";

    public static final String AUTHORIZATION_USER_ID = "userId";

    public static final String REFERENCED_SESSION_ID_SOURCE = "Stateless request";

    // 用户redis缓存
    public static final String USER_CACHE_ = "user_cache_";

    // token失效时间
    public static final int TOKEN_EXPIRE_TIME = 24 * 60 * 60;


}
