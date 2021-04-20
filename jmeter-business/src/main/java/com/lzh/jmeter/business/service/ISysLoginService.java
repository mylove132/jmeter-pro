package com.lzh.jmeter.business.service;

import com.lzh.jmeter.system.api.model.LoginUser;

public interface ISysLoginService {

    LoginUser login(String userName, String password);
}
