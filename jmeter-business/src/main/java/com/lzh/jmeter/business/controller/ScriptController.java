package com.lzh.jmeter.business.controller;
import com.lzh.jmeter.commons.core.domain.ResponseData;
import com.lzh.jmeter.commons.core.domain.ResponseUtil;
import com.lzh.jmeter.commons.log.annotation.Log;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/script")
public class ScriptController {

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @Log(title = "测试获取脚本列表---------------")
    public ResponseData getScriptListController (HttpServletRequest request) {
        System.out.println("888888888888888888888888888"+request.getContextPath());
        return new ResponseUtil().success("ok");
    }
}
