package com.lzh.jdh.qa.business.service.impl;

import com.lzh.jdh.qa.business.service.ISysLoginService;
import com.lzh.jdh.qa.commons.core.domain.ResponseData;
import com.lzh.jdh.qa.commons.core.exception.BaseBusinessException;
import com.lzh.jdh.qa.commons.core.utils.SecurityUtils;
import com.lzh.jdh.qa.commons.core.utils.StringUtils;
import com.lzh.jdh.qa.system.api.domain.SysUser;
import com.lzh.jdh.qa.system.api.model.LoginUser;
import com.lzh.jdh.qa.system.api.service.ISysUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Component;

/**
 * 登录校验方法
 * 
  */
@Slf4j
@Component
public class SysLoginService implements ISysLoginService {

    @DubboReference
    private ISysUserService sysUserService;

    /**
     * 登录
     */
    public LoginUser login(String username, String password)
    {
        // 用户名或密码为空 错误
        if (StringUtils.isAnyBlank(username, password))
        {
            throw new BaseBusinessException(20001, "用户/密码必须填写");
        }
        // 查询用户信息
        ResponseData<LoginUser> userResult = sysUserService.selectSysUserByName(username);
        if (StringUtils.isNull(userResult) || StringUtils.isNull(userResult.getData()))
        {
            throw new BaseBusinessException(20002, "登录用户：" + username + " 不存在");
        }
        LoginUser userInfo = userResult.getData();
        SysUser user = userResult.getData().getSysUser();
        if ("1".equals(user.getDeleted()))
        {
            throw new BaseBusinessException(20003, "对不起，您的账号：" + username + " 已被删除");
        }
        if ("1".equals(user.getStatus()))
        {
            throw new BaseBusinessException(20004, "对不起，您的账号：" + username + " 已停用");
        }
        if (!SecurityUtils.matchesPassword(password, user.getPassword()))
        {
            throw new BaseBusinessException(20005, "用户不存在/密码错误");
        }
        return userInfo;
    }
}