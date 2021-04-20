package com.lzh.jmeter.system.main.mapper;
import com.lzh.jmeter.system.api.domain.SysOperLog;
import com.lzh.jmeter.system.api.domain.SysUser;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 用户查询
 *
 */
@Component
public interface SysUserMapper
{
  public SysUser selectUserByname (String userName);
}
