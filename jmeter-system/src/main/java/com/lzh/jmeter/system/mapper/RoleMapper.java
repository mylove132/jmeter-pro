package com.lzh.jmeter.system.mapper;

import com.lzh.jmeter.system.domain.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper {

    List<Role> getUserRoles(@Param("userId") Integer userId);
}
