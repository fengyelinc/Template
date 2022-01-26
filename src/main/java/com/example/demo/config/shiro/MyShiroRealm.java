package com.example.demo.config.shiro;

import com.example.demo.config.shiro.realm.CustomToken;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.service.RoleService;
import com.example.demo.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * 描述：
 *
 * @author cc
 * @create 2019-01-27-13:57
 */
public class MyShiroRealm extends AuthorizingRealm {

@Autowired
@Lazy
private RoleService roleService;

@Autowired
@Lazy
private UserService userService;
    /**
     * 获取授权信息
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("-------身份授权方法--------");
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //获取身份信息
        String account = (String) principalCollection.getPrimaryPrincipal();
        //根据主身份信息获取角色 和 权限信息
        Set<String> roles = new HashSet<>();
        Role role = roleService.getRoleByUserName(account);
        roles.add(role.getName());
        info.setRoles(roles);
        return info;

    }

    /**
     * private UserService userService;
     * <p>
     * 获取身份验证信息
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("-------身份认证方法--------");
        CustomToken customToken = (CustomToken)token;   //这里的token是从登录接口（/login/main）里获取的
        String account = (String) customToken.getPrincipal();
        User user =userService.selectUserByAccount(account);

        if(Objects.isNull(user)){
            throw new UnknownAccountException();//没找到帐号
        }

        return new SimpleAuthenticationInfo(user.getAccount(),  //自定义Authentication对象，使得Subject除了携带用户的登录名外还可以携带更多信息.
                user.getPassword(),                        //密码
                ByteSource.Util.bytes(user.getSalt().getBytes()),      //加盐
                getName());                                //realm name
    }
}
