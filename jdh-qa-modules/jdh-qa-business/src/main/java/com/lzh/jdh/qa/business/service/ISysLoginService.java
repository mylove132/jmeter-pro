package com.lzh.jdh.qa.business.service;

import com.lzh.jdh.qa.system.api.model.LoginUser;

public interface ISysLoginService {

    LoginUser login(String userName, String password);
}
