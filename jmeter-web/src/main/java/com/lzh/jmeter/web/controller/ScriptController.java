package com.lzh.jmeter.web.controller;

import com.lzh.jmeter.commons.core.domain.ResponseData;
import com.lzh.jmeter.commons.core.domain.ResponseUtil;
import com.lzh.jmeter.commons.core.web.controller.BaseController;
import com.lzh.jmeter.commons.log.annotation.Log;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: liuzhanhui
 * @Decription:
 * @Date: Created in 2021-04-18:23:03
 * Modify date: 2021-04-18:23:03
 */
@RestController
public class ScriptController extends BaseController {

    @Log(title = "获取脚本列表")
    @RequestMapping("/scriptList")
    public ResponseData getScriptsController () {
        Map<String, String> maps = new HashMap<>();
        maps.put("name", "张三");
        return new ResponseUtil().success(maps);
    }
}
