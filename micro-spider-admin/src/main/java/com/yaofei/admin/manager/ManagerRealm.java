package com.yaofei.admin.manager;

import com.yaofei.admin.manager.account.domain.Account;
import com.yaofei.admin.manager.account.service.AccountService;
import com.yaofei.admin.manager.role.entity.Role;
import com.yaofei.admin.manager.role.service.RoleService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by fei.yao on 2016/10/19.
 */
public class ManagerRealm extends AuthorizingRealm {
    private static final Logger LOGGER = LoggerFactory.getLogger(ManagerRealm.class);

    @Resource
    AccountService accountService;

    @Resource
    RoleService roleService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String currentLoginName = (String) principals.getPrimaryPrincipal();
        List<String> managerRoles = new ArrayList<String>();
        List<String> managerPermissions = new ArrayList<String>();
        //从数据库中获取当前登录用户的详细信息
        Account account = accountService.findByLoginName(currentLoginName);
        if (null != account) {
            //获取当前用户下所有ACL权限列表  待续。。。
            //获取当前用户下拥有的所有角色列表
            List<Role> roles = roleService.findByUserId(account.getId());
            if(roles != null && roles.size() > 0){
                for (Role role : roles) {
                    managerRoles.add(role.getCode());
                }
            }
        } else {
            throw new AuthorizationException();
        }
        System.out.println("#######获取角色：" + managerRoles);
        System.out.println("#######获取权限：" + managerPermissions);
        //为当前用户设置角色和权限
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.addRoles(managerRoles);
        authorizationInfo.addStringPermissions(managerPermissions);
        return authorizationInfo;
    }

    /**
     * 验证当前登录的Subject
     * LoginController.login()方法中执行Subject.login()时 执行此方法
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authToken) throws AuthenticationException {
        LOGGER.info("###【开始认证[SessionId]】" + SecurityUtils.getSubject().getSession().getId());
        String loginName = (String) authToken.getPrincipal();
        Account account = accountService.findByLoginName(loginName);
        if (account == null) {
            throw new UnknownAccountException();//没找到帐号
        }
        //交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，如果觉得人家的不好可以自定义实现
        return new SimpleAuthenticationInfo(
                account.getUserName(), //用户名
                account.getPassword(), //密码
//                ByteSource.Util.bytes(account.getCredentialsSalt()),//salt=username+salt,采用明文访问时，不需要此句
                getName()  //realm name
        );
    }
}
