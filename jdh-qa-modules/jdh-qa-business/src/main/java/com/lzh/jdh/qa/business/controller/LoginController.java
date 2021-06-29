package com.lzh.jdh.qa.business.controller;

import com.lzh.jdh.qa.business.domain.request.LoginVO;
import com.lzh.jdh.qa.business.domain.rsponse.TokenVO;
import com.lzh.jdh.qa.business.service.ISysLoginService;
import com.lzh.jdh.qa.commons.core.domain.ResponseData;
import com.lzh.jdh.qa.commons.core.domain.ResponseUtil;
import com.lzh.jdh.qa.security.service.TokenService;
import com.lzh.jdh.qa.system.api.model.LoginUser;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class LoginController {

    private final ISysLoginService sysLoginService;

    private final TokenService tokenService;

    public LoginController(ISysLoginService sysLoginService, TokenService tokenService) {
        this.sysLoginService = sysLoginService;
        this.tokenService = tokenService;
    }

    @PostMapping("login")
    public ResponseData<?> login(@RequestBody LoginVO loginVO) {
        TokenVO tokenVO = new TokenVO();
        LoginUser loginUser = sysLoginService.login(loginVO.getUsername(), loginVO.getPassword());
        Map<String, String> tokenResult = tokenService.createToken(loginVO.getUsername());
        tokenVO.setExpireTime(tokenResult.get("expireTime"));
        tokenVO.setToken(tokenResult.get("token"));
        tokenVO.setUserName(loginVO.getUsername());
        tokenVO.setUserId(loginUser.getSysUser().getUserId());
       return  new ResponseUtil().success(tokenVO);
    }
}