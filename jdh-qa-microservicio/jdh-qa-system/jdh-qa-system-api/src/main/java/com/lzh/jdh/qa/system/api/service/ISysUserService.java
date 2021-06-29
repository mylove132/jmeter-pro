package com.lzh.jdh.qa.system.api.service;

import com.lzh.jdh.qa.commons.core.domain.ResponseData;
import com.lzh.jdh.qa.commons.core.exception.BizException;
import com.lzh.jdh.qa.system.api.domain.SysRole;
import com.lzh.jdh.qa.system.api.model.LoginUser;

public interface ISysUserService {

    ResponseData<LoginUser> selectSysUserByName (String userName) throws BizException;

    ResponseData<SysRole> selectSysRoleByRoleId(Long roleId) throws BizException;
}
