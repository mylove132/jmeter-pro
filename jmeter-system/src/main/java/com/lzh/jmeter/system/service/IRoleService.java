package com.lzh.jmeter.system.service;

import com.lzh.jmeter.system.domain.Role;

import java.util.List;

public interface IRoleService {
    List<Role> getUserRoles(Integer userId);
}
