package com.lzh.jdh.qa.security.handle;

import com.lzh.jdh.qa.commons.core.constant.Constants;
import com.lzh.jdh.qa.commons.core.domain.ResponseData;
import com.lzh.jdh.qa.commons.core.domain.ResponseUtil;
import com.lzh.jdh.qa.commons.core.exception.BaseBusinessException;
import com.lzh.jdh.qa.commons.core.exception.BizException;
import com.lzh.jdh.qa.commons.core.exception.PreAuthorizeException;
import com.lzh.jdh.qa.commons.core.exception.ValidateException;
import com.lzh.jdh.qa.commons.core.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Author: liuzhanhui
 * @Decription:
 * @Date: Created in 2021-04-18:22:14
 * Modify date: 2021-04-18:22:14
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 基础异常
     */
    @ExceptionHandler(BaseBusinessException.class)
    public ResponseData baseException(BaseBusinessException e) {
        if (StringUtils.isNull(e.getErrorCode())) {
            return new ResponseUtil().fail(e);
        }
        return new ResponseUtil().fail(e.getErrorCode(), e.getMessage());
    }

    /**
     * 业务异常
     */
    @ExceptionHandler(BizException.class)
    public ResponseData businessException(BizException e) {
        if (StringUtils.isNull(e.getErrorCode())) {
            return new ResponseUtil().fail(e);
        }
        return new ResponseUtil().fail(e.getErrorCode(), e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseData handleException(Exception e) {
        log.error(e.getMessage(), e);
        return new ResponseUtil().fail(e);
    }

    /**
     * 自定义验证异常
     */
    @ExceptionHandler(ValidateException.class)
    public ResponseData validatedBindException(ValidateException e) {
        log.error(e.getMessage(), e);
        return new ResponseUtil().fail(e);
    }

    /**
     * 权限异常
     */
    @ExceptionHandler(PreAuthorizeException.class)
    public ResponseData preAuthorizeException(PreAuthorizeException e) {
        return new ResponseUtil().fail(Constants.NOT_PREMISSION, e.getMessage());
    }

}
