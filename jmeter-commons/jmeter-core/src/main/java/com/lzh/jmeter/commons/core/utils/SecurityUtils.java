package com.lzh.jmeter.commons.core.utils;
import javax.servlet.http.HttpServletRequest;
import com.lzh.jmeter.commons.core.constant.CacheConstants;
import com.lzh.jmeter.commons.core.text.Convert;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * 权限获取工具类
 *
 */
public class SecurityUtils
{
    /**
     * 获取用户
     */
    public static String getUsername()
    {
        String username = ServletUtils.getRequest().getHeader(CacheConstants.DETAILS_USERNAME);
        return ServletUtils.urlDecode(username == null ? "张三" : username);
    }

    /**
     * 获取用户ID
     */
    public static Long getUserId()
    {
        return Convert.toLong(ServletUtils.getRequest().getHeader(CacheConstants.DETAILS_USER_ID));
    }

    /**
     * 获取请求token
     */
    public static String getToken()
    {
        return getToken(ServletUtils.getRequest());
    }

    /**
     * 根据request获取请求token
     */
    public static String getToken(HttpServletRequest request)
    {
        String token = ServletUtils.getRequest().getHeader(CacheConstants.HEADER);
        return token;
    }

    /**
     * 是否为管理员
     *
     * @param userId 用户ID
     * @return 结果
     */
    public static boolean isAdmin(Long userId)
    {
        return userId != null && 1L == userId;
    }

    /**
     * 生成BCryptPasswordEncoder密码
     *
     * @param password 密码
     * @return 加密字符串
     */
    public static String encryptPassword(String password)
    {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }

    /**
     * 判断密码是否相同
     *
     * @param rawPassword 真实密码
     * @param encodedPassword 加密后字符
     * @return 结果
     */
    public static boolean matchesPassword(String rawPassword, String encodedPassword)
    {
        if (StringUtils.isBlank(rawPassword) || StringUtils.isNull(rawPassword)) {
            return false;
        }
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
}