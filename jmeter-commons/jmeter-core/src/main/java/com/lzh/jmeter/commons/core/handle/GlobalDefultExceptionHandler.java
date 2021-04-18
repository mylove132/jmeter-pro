package com.lzh.jmeter.commons.core.handle;

import com.lzh.jmeter.commons.core.domain.ResponseData;
import com.lzh.jmeter.commons.core.domain.ResponseUtil;
import com.lzh.jmeter.commons.core.exception.BaseBusinessException;
import com.lzh.jmeter.commons.core.exception.BizException;
import com.lzh.jmeter.commons.core.exception.CommonExceptionEnumInterface;
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
    public <T> ResponseData<?> defultExcepitonHandler(HttpServletRequest request, Exception e) {
        e.printStackTrace();
        if(e instanceof BaseBusinessException) {
            BaseBusinessException businessException = (BaseBusinessException)e;
            return new ResponseUtil().fail(businessException.getErrorCode(), businessException.getMessage());
        }
        //未知错误
        return new ResponseUtil().fail(CommonExceptionEnumInterface.FAIL);
    }

    //通用异常
    @ExceptionHandler(BizException.class)
    @ResponseBody
    public <T> ResponseData<?> bizExcepitonHandler(Exception e) {
        e.printStackTrace();
        if(e instanceof BizException) {
            BizException bizException = (BizException)e;
            return new ResponseUtil().fail(bizException.getErrorCode(), bizException.getMessage());
        }
        //未知错误
        return new ResponseUtil().fail(CommonExceptionEnumInterface.FAIL);
    }
}