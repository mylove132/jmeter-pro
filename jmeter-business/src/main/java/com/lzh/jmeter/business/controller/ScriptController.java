package com.lzh.jmeter.business.controller;
import com.lzh.jmeter.business.domain.vo.UIScriptVO;
import com.lzh.jmeter.commons.core.domain.ResponseData;
import com.lzh.jmeter.commons.core.domain.ResponseUtil;
import com.lzh.jmeter.commons.log.annotation.Log;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ScriptController {

    @RequestMapping(value = "/api/script/list", method = RequestMethod.GET)
    @Log(title = "测试获取脚本列表---------------")
    public ResponseData getScriptListController (HttpServletRequest request) {
        System.out.println("888888888888888888888888888"+request.getContextPath());
        return new ResponseUtil().success("ok");
    }

    @RequestMapping(value = "/api/ui-script/list", method = RequestMethod.GET)
    @Log(title = "获取UI脚本列表---------------")
    public ResponseData queryUIScriptListController (HttpServletRequest request) {
        List<UIScriptVO> voList = new ArrayList<>();
        for (int i = 0; i <= 5; i++) {
            UIScriptVO scriptVO = new UIScriptVO();
            scriptVO.setCode("public static void main"+i);
            scriptVO.setDesc("desc"+i);
            scriptVO.setId(i);
            scriptVO.setName("测试UI脚本："+i);
            scriptVO.setLang("java");
            scriptVO.setTheme("chrome");
            voList.add(scriptVO);
        }
        return new ResponseUtil().success(voList);
    }
}
