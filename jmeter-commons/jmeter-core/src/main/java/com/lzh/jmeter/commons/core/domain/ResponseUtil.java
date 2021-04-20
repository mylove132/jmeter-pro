package com.lzh.jmeter.commons.core.domain;

import com.lzh.jmeter.commons.core.constant.Constants;
import com.lzh.jmeter.commons.core.domain.ResponseData;
import com.lzh.jmeter.commons.core.exception.ExceptionEnumInterface;

/**
 * @Author: liuzhanhui
 * @Decription:
 * @Date: Created in 2021-04-18:07:45
 * Modify date: 2021-04-18:07:45
 */
public class ResponseUtil<T> {

    private ResponseData<T> responseData = new ResponseData<>();

    public ResponseData<T> success(T data){
        this.responseData.setCode(Constants.SUCCESS);
        this.responseData.setMessage("OK");
        this.responseData.setData(data);
        return this.responseData;
    }

    public ResponseData<T> success(){
        this.responseData.setCode(Constants.SUCCESS);
        this.responseData.setMessage("OK");
        this.responseData.setData(null);
        return this.responseData;
    }

    public ResponseData<T> success(String msg){
        this.responseData.setCode(Constants.SUCCESS);
        this.responseData.setMessage(msg);
        this.responseData.setData(null);
        return this.responseData;
    }

    public ResponseData<T> fail(){
        this.responseData.setCode(Constants.FAIL);
        this.responseData.setMessage("error");
        return this.responseData;
    }
    public ResponseData<T> fail(Throwable e){
        this.responseData.setCode(Constants.FAIL);
        this.responseData.setMessage(e.getMessage());
        return this.responseData;
    }

    public ResponseData<T> fail(int code, String message){
        this.responseData.setCode(code);
        this.responseData.setMessage(message);
        return this.responseData;
    }

    public ResponseData<T> fail(ExceptionEnumInterface exceptionEnumInterface){
        this.responseData.setCode(exceptionEnumInterface.getCode());
        this.responseData.setMessage(exceptionEnumInterface.getMessage());
        this.responseData.setData(null);
        return this.responseData;
    }

}
