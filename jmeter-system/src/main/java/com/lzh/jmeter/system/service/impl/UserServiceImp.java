package com.lzh.jmeter.system.service.impl;
import com.lzh.jmeter.commons.core.constant.CacheConstants;
import com.lzh.jmeter.commons.redis.service.RedisService;
import com.lzh.jmeter.system.bean.CacheUser;
import com.lzh.jmeter.system.domain.User;
import com.lzh.jmeter.system.exception.LoginException;
import com.lzh.jmeter.system.mapper.UserMapper;
import com.lzh.jmeter.system.service.IUserService;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

@Service
public class UserServiceImp implements IUserService {

    private Logger log = LoggerFactory.getLogger(UserServiceImp.class);

    private final RedisService redisService;

    private final UserMapper userMapper;

    public UserServiceImp(UserMapper userMapper, RedisService redisService) {
        this.userMapper = userMapper;
        this.redisService = redisService;
    }

    @Override
    public User findByUsername(String userName) {
        return userMapper.findByUsername(userName);
    }

    @Override
    public User findByToken(String token) {
        return userMapper.findByToken(token);
    }

    @Override
    public CacheUser login(String userName, String password) {
        // 获取Subject实例对象，用户实例
        Subject currentUser = SecurityUtils.getSubject();
        // 将用户名和密码封装到UsernamePasswordToken
        UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
        CacheUser cacheUser;
        // 4、认证
        try {
            // 传到 MyShiroRealm 类中的方法进行认证
            currentUser.login(token);
            // 构建缓存用户信息返回给前端
            User user = (User) currentUser.getPrincipals().getPrimaryPrincipal();
            cacheUser = CacheUser.builder()
                    .token(currentUser.getSession().getId().toString())
                    .build();
            BeanUtils.copyProperties(user, cacheUser);
            log.warn("CacheUser is {}", cacheUser.toString());
        } catch (UnknownAccountException e) {
            log.error("账户不存在异常：", e);
            throw new LoginException("账号不存在!", e);
        } catch (IncorrectCredentialsException e) {
            log.error("凭据错误（密码错误）异常：", e);
            throw new LoginException("密码不正确!", e);
        } catch (AuthenticationException | IllegalAccessException | InvocationTargetException e) {
            log.error("身份验证异常:", e);
            throw new LoginException("用户验证失败!", e);
        }

        // 向缓存放入用户信息
        String userRedisKey = CacheConstants.USER_CACHE_ + cacheUser.getUserId();
        if (redisService.hasKey(userRedisKey)){
            redisService.del(userRedisKey);
        }
        redisService.set(userRedisKey, cacheUser, CacheConstants.TOKEN_EXPIRE_TIME);

        return cacheUser;

    }

    @Override
    public void logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
    }

    @Override
    public List<User> listUsers() {
        return userMapper.userList();
    }
}
