package com.lzh.jmeter.system.mapper;

import com.lzh.jmeter.system.domain.Permission;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface PermissionMapper {
    List<Permission> getRolePermissions(@Param("roleId") Integer roleId);
}
