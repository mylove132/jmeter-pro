package com.lzh.jmeter.system.api.domain.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lzh.jmeter.commons.core.domain.AbstractRequest;
import com.lzh.jmeter.commons.core.exception.ValidateException;
import com.lzh.jmeter.commons.core.utils.StringUtils;
import com.lzh.jmeter.system.api.errorcode.SystemCode;
import lombok.Data;

import java.util.Date;

/**
 * @Author: liuzhanhui
 * @Decription:
 * @Date: Created in 2021-04-18:07:21
 * Modify date: 2021-04-18:07:21
 */
@Data
public class SysOperLogRequest extends AbstractRequest {

    private static final long serialVersionUID = 1L;

    /** 日志主键 */
    private Long operId;

    /** 操作模块 */
    private String title;

    /** 业务类型（0其它 1新增 2修改 3删除） */
    private Integer businessType;

    /** 业务类型数组 */
    private Integer[] businessTypes;

    /** 请求方法 */
    private String method;

    /** 请求方式 */
    private String requestMethod;

    /** 操作类别（0其它 1后台用户 2手机端用户） */
    private Integer operatorType;

    /** 操作人员 */
    private String operName;

    /** 部门名称 */
    private String deptName;

    /** 请求url */
    private String operUrl;

    /** 操作地址 */
    private String operIp;

    /** 请求参数 */
    private String operParam;

    /** 返回参数 */
    private String jsonResult;

    /** 操作状态（0正常 1异常） */
    private Integer status;

    /** 错误消息 */
    private String errorMsg;

    /** 操作时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date operTime;


    @Override
    public void requestCheck() {
        if (StringUtils.isBlank(operName) || StringUtils.isBlank(title)) {
            throw new ValidateException(SystemCode.LOG_REQUISITE_PARAMETER_NOT_EXIST.getCode(), SystemCode.LOG_REQUISITE_PARAMETER_NOT_EXIST.getMessage());
        }
    }
}
