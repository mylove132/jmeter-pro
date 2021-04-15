package com.lzh.jmeter.system.controller;

import com.lzh.jmeter.commons.core.constant.JmeterConstants;
import com.lzh.jmeter.commons.core.domain.R;
import com.lzh.jmeter.commons.core.utils.Md5Utils;
import com.lzh.jmeter.commons.core.web.controller.BaseController;
import com.lzh.jmeter.commons.core.web.page.TableDataInfo;
import com.lzh.jmeter.commons.log.annotation.Log;
import com.lzh.jmeter.commons.log.enums.BusinessType;
import com.lzh.jmeter.system.domain.JmeterScript;
import com.lzh.jmeter.system.service.IJmeterScriptService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ScriptController extends BaseController {

    private Logger log = LoggerFactory.getLogger(ScriptController.class);

    private final IJmeterScriptService jmeterScriptService;

    public ScriptController(IJmeterScriptService jmeterScriptService) {
        this.jmeterScriptService = jmeterScriptService;
    }

    @GetMapping("/script/{scriptId}")
    public R<JmeterScript> queryScriptByIdController(@PathVariable Long scriptId) {
        JmeterScript script = jmeterScriptService.queryScriptByIdService(scriptId);
       return R.ok(script);
    }

    @PostMapping("/script/add")
    public R<Boolean> addScriptByIdController(@RequestBody JmeterScript jmeterScript) {
       jmeterScriptService.addScriptService(jmeterScript);
        return R.ok(true);
    }

    @Log(title = "获取脚本列表")
    @GetMapping("/script/list")
    public TableDataInfo queryScriptByIdController() {
        startPage();
        List<JmeterScript> scripts = jmeterScriptService.queryScriptsService();
        return getDataTable(scripts);
    }

    @Log(title = "上传脚本", businessType = BusinessType.IMPORT)
    @PostMapping("/script/upload")
    public R<Map> scriptUploadController(@RequestParam("file") MultipartFile file) {
        Map<String, String> fileMd5Map = new HashMap<>();
        String suffix = ".jmx";
        String originalFilename = file.getOriginalFilename();
        String fileName = originalFilename.split(suffix)[0];
        String md5Name = Md5Utils.md5(fileName);
        fileMd5Map.put("name", fileName);
        fileMd5Map.put("md5", md5Name);
        try {
            file.transferTo(new File(JmeterConstants.JMETER_FILE_PATH + md5Name + suffix));
        } catch (IOException e) {
            throw new RuntimeException("文件保存失败", e);
        }
        return R.ok(fileMd5Map);
    }
}
