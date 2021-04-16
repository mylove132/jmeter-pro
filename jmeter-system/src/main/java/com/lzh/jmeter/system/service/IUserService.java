package com.lzh.jmeter.system.service;

import com.lzh.jmeter.system.bean.CacheUser;
import com.lzh.jmeter.system.domain.User;

import java.util.List;

public interface IUserService {

    User findByUsername(String userName);

    User findByToken(String token);

    CacheUser login(String userName, String password);


    void logout();

    List<User> listUsers();
}
