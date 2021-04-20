package com.lzh.jmeter.system.api.service;

import com.lzh.jmeter.commons.core.domain.ResponseData;
import com.lzh.jmeter.commons.core.exception.BizException;
import com.lzh.jmeter.system.api.domain.SysRole;
import com.lzh.jmeter.system.api.model.LoginUser;

public interface ISysUserService {

    ResponseData<LoginUser> selectSysUserByName (String userName) throws BizException;

    ResponseData<SysRole> selectSysRoleByRoleId(Long roleId) throws BizException;
}
