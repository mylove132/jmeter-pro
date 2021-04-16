package com.lzh.jmeter.system.controller;
import com.lzh.jmeter.commons.core.domain.R;
import com.lzh.jmeter.system.bean.CacheUser;
import com.lzh.jmeter.system.domain.User;
import com.lzh.jmeter.system.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;

@Slf4j
@RestController
public class LoginController {

    @Resource
    private IUserService iUserService;

    @PostMapping("/login")
    public R login(User user) {
        log.warn("进入登录.....");
        String userName = user.getUserName();
        String password = user.getPassword();
        if (StringUtils.isBlank(userName)) {
            return R.fail("用户名为空！");
        }
        if (StringUtils.isBlank(password)) {
            return R.fail("密码为空！");
        }
        CacheUser loginUser = iUserService.login(userName, password);
        // 登录成功返回用户信息
        return R.ok(loginUser);
    }

    @RequestMapping("/logout")
    public R logOut() {
        iUserService.logout();
        return R.ok("登出成功！");
    }

    @RequestMapping("/un_auth")
    public R unAuth() {
        return R.fail( "用户未登录！");
    }

    @RequestMapping("/unauthorized")
    public R unauthorized() {
        return R.fail("用户无权限！");
    }
}
