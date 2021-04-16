package com.lzh.jmeter.system.domain;

import java.io.Serializable;

public class RolePermission implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Integer permissionId;

    private Integer roleId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Integer permissionId) {
        this.permissionId = permissionId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}
