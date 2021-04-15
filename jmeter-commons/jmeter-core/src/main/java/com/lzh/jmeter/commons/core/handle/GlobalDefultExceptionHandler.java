package com.lzh.jmeter.commons.core.handle;

import com.lzh.jmeter.commons.core.domain.R;
import com.lzh.jmeter.commons.core.exception.BaseException;
import com.lzh.jmeter.commons.core.exception.job.TaskException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常捕获
 */
@ControllerAdvice
public class GlobalDefultExceptionHandler {

    private Logger log = LoggerFactory.getLogger(GlobalDefultExceptionHandler.class);

    //通用异常
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public <T> R<?> defultExcepitonHandler(HttpServletRequest request, Exception e) {
        e.printStackTrace();
        if(e instanceof BaseException) {
            BaseException businessException = (BaseException)e;
            return R.fail(businessException.getCode(), businessException.getMessage());
        }
        //未知错误
        return R.fail(-1, "系统异常：\\n"+e);
    }

    //定时任务异常
    @ExceptionHandler(TaskException.class)
    @ResponseBody
    public <T> R<?> taskExcepitonHandler(HttpServletRequest request, Exception e) {
        e.printStackTrace();
        if(e instanceof TaskException) {
            TaskException businessException = (TaskException)e;
            return R.fail(businessException.getCode(), businessException.getMessage());
        }
        //未知错误
        return R.fail(-1, "系统异常：\\n"+e);
    }

}