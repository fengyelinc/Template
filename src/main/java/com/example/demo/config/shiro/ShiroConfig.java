package com.example.demo.config.shiro;


import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 描述：
 *
 * @author cc
 * @create 2019-01-27-13:38
 */
@Configuration
public class ShiroConfig {

    /**
     * 过滤接口，设置访问权限
     * @param securityManager  下面定义的securityManager
     * @return
     */
    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setSecurityManager(securityManager);

        Map<String, String> filterMap = new LinkedHashMap<>();
        // <!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->
        filterMap.put("/login", "authc");
        filterMap.put("/login/main", "anon");
        filterMap.put("/static/**","anon");
        filterMap.put("/user/**", "authc");
        filterMap.put("/**", "authc");           //主要这行代码必须放在所有权限设置的最后，不然会导致所有 url 都被拦截 剩余的都需要认证
        bean.setFilterChainDefinitionMap(filterMap);

        bean.setLoginUrl("/login");            //没有权限就跳转登录页
        //bean.setSuccessUrl("/index");         //登录成功后跳转(不起效)

        return bean;

    }

    /**
     * @param myShiroRealm  下面定义的customRealm（）交给spring管理后，可在参数里直接获取
     * @return
     */
    @Bean
    public SecurityManager securityManager(MyShiroRealm myShiroRealm) {
        DefaultWebSecurityManager defaultSecurityManager = new DefaultWebSecurityManager();
        defaultSecurityManager.setRealm(myShiroRealm);
        return defaultSecurityManager;
    }

    /**
     * bean名字默认是方法名
     * @return
     */
    @Bean
    public MyShiroRealm customRealm() {
        MyShiroRealm myShiroRealm = new MyShiroRealm();
        return myShiroRealm;
    }
}

