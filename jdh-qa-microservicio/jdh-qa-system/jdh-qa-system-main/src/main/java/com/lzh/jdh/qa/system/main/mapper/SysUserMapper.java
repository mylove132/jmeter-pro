package com.lzh.jdh.qa.system.main.mapper;
import com.lzh.jdh.qa.system.api.domain.SysUser;
import org.springframework.stereotype.Component;

/**
 * 用户查询
 *
 */
@Component
public interface SysUserMapper
{
  public SysUser selectUserByname (String userName);
}
