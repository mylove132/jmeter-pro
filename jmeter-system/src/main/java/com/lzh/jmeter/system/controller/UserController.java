package com.lzh.jmeter.system.controller;

import com.lzh.jmeter.commons.core.domain.R;
import com.lzh.jmeter.system.domain.User;
import com.lzh.jmeter.system.service.IUserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService iUserService;


    @GetMapping("/userList")
    @RequiresPermissions("user:view")//权限管理;
    public R listUsers(){
        List<User> users = iUserService.listUsers();
        return R.ok(users);
    }

    @PostMapping("/userAdd")
    @RequiresPermissions("user:add")//权限管理;
    public String userInfoAdd(){
        return "userAdd";
    }


    @DeleteMapping("/userDel")
    @RequiresPermissions("user:del")//权限管理;
    public String userDel(){
        return "userDel";
    }

}
