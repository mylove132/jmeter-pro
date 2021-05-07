package com.lzh.jmeter.business.controller;
import com.lzh.jmeter.business.domain.request.RunUIScriptVO;
import com.lzh.jmeter.business.domain.rsponse.UIScriptVO;
import com.lzh.jmeter.business.exception.UIScriptExceptionEnum;
import com.lzh.jmeter.business.utils.CustomStringJavaCompiler;
import com.lzh.jmeter.commons.core.domain.ResponseData;
import com.lzh.jmeter.commons.core.domain.ResponseUtil;
import com.lzh.jmeter.commons.core.exception.BizException;
import com.lzh.jmeter.commons.core.exception.CommonExceptionEnumInterface;
import com.lzh.jmeter.commons.core.exception.ValidateException;
import com.lzh.jmeter.commons.core.utils.StringUtils;
import com.lzh.jmeter.commons.log.annotation.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ScriptController {

    @Value("${config.extdirs}")
    private String extdirs;

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

    @RequestMapping(value = "/api/ui-script/run", method = RequestMethod.POST)
    @Log(title = "运行UI脚本")
    public ResponseData runUIScriptListController (@RequestBody RunUIScriptVO runUIScriptVO) {
        System.out.println("接口参数："+runUIScriptVO.toString());
        if (StringUtils.isBlank(runUIScriptVO.getCode())) {
            throw new ValidateException(CommonExceptionEnumInterface.PARAM_NOT_NULL);
        }
        CustomStringJavaCompiler customStringJavaCompiler = new CustomStringJavaCompiler(runUIScriptVO.getCode());
        boolean compilerResult = customStringJavaCompiler.compiler(extdirs);
        if (!compilerResult) {
            throw new BizException(UIScriptExceptionEnum.COMPILER_FAIL);
        }
        try {
            customStringJavaCompiler.runMainMethod();
        } catch (ClassNotFoundException e) {
            throw new BizException(UIScriptExceptionEnum.RUN_CODE_FAIL);
        } catch (NoSuchMethodException e) {
            throw new BizException(UIScriptExceptionEnum.RUN_CODE_FAIL);
        } catch (InvocationTargetException e) {
            throw new BizException(UIScriptExceptionEnum.RUN_CODE_FAIL);
        } catch (IllegalAccessException e) {
            throw new BizException(UIScriptExceptionEnum.RUN_CODE_FAIL);
        } catch (UnsupportedEncodingException e) {
            throw new BizException(UIScriptExceptionEnum.RUN_CODE_FAIL);
        }
        return new ResponseUtil().success(customStringJavaCompiler.getRunResult());
    }
}
