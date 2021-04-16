package com.lzh.jmeter.system.config;

import com.lzh.jmeter.system.domain.Permission;
import com.lzh.jmeter.system.domain.Role;
import com.lzh.jmeter.system.domain.User;
import com.lzh.jmeter.system.service.IPermissionService;
import com.lzh.jmeter.system.service.IRoleService;
import com.lzh.jmeter.system.service.IUserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Objects;

public class MyShiroRealm extends AuthorizingRealm {

    private Logger log = LoggerFactory.getLogger(MyShiroRealm.class);

    @Autowired
    private IUserService iUserService;

    @Autowired
    private IRoleService iRoleService;

    @Autowired
    private IPermissionService iPermissionService;


    /**
     * @return 权限信息，包括角色以及权限
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        log.warn("开始执行授权操作.......");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        //如果身份认证的时候没有传入User对象，这里只能取到userName
        //也就是SimpleAuthenticationInfo构造的时候第一个参数传递需要User对象
        User user = (User) principals.getPrimaryPrincipal();

        // 查询用户角色，一个用户可能有多个角色
        List<Role> roles = iRoleService.getUserRoles(user.getUserId());

        for (Role role : roles) {
            authorizationInfo.addRole(role.getRole());
            // 根据角色查询权限
            List<Permission> permissions = iPermissionService.getRolePermissions(role.getRoleId());
            for (Permission p : permissions) {
                authorizationInfo.addStringPermission(p.getPermission());
            }
        }
        return authorizationInfo;
    }

    /**
     *
     * @return 身份验证信息
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        log.warn("开始进行身份认证......");

        //获取用户的输入的账号.
        String userName = (String) token.getPrincipal();

        //通过username从数据库中查找 User对象.
        //实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
        User user = iUserService.findByUsername(userName);
        if (Objects.isNull(user)) {
            return null;
        }

        return new SimpleAuthenticationInfo(
                // 这里传入的是user对象，比对的是用户名，直接传入用户名也没错，但是在授权部分就需要自己重新从数据库里取权限
                user,
                // 密码
                user.getPassword(),
                // salt = username + salt
                ByteSource.Util.bytes(user.getCredentialsSalt()),
                // realm name
                getName()
        );
    }

}
