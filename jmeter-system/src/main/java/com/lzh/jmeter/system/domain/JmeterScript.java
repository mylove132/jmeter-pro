package com.lzh.jmeter.system.domain;

import com.lzh.jmeter.commons.core.web.domain.BaseEntity;

/**
 * jmeter脚本实体
 */
public class JmeterScript extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Long scriptId;

    private String scriptName;

    // 线程数
    private Integer threadNum;

    // 压测时长 (分钟)
    private Integer stressTime;

    // 文件md5值
    private String fileMd5;

    // 脚本运行状态
    private Boolean status;

    public Long getScriptId() {
        return scriptId;
    }

    public void setScriptId(Long scriptId) {
        this.scriptId = scriptId;
    }

    public String getScriptName() {
        return scriptName;
    }

    public void setScriptName(String scriptName) {
        this.scriptName = scriptName;
    }

    public Integer getThreadNum() {
        return threadNum;
    }

    public void setThreadNum(Integer threadNum) {
        this.threadNum = threadNum;
    }

    public Integer getStressTime() {
        return stressTime;
    }

    public void setStressTime(Integer stressTime) {
        this.stressTime = stressTime;
    }

    public String getFileMd5() {
        return fileMd5;
    }

    public void setFileMd5(String fileMd5) {
        this.fileMd5 = fileMd5;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
