package com.example.demo.config.shiro;


import com.example.demo.config.cache.RedisCacheManger;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 描述：
 *  用来整合shiro框架的相关配置类
 * @author cc
 * @create 2019-01-27-13:38
 */
@Configuration
public class ShiroConfig {

    /**
     * 拦截所有请求request，，区分访问公共资源/受限资源
     * 过滤接口，设置访问权限
     * @param securityManager  下面定义的securityManager
     * @return
     */
    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        //给filter设置安全管理器
        bean.setSecurityManager(securityManager);

        Map<String, String> filterMap = new LinkedHashMap<>();
        // <!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->
        //配置系统公共资源
        filterMap.put("/login", "anon");
        filterMap.put("/login/main", "anon");
        filterMap.put("/register", "anon");
        filterMap.put("/submit/register", "anon");
        filterMap.put("/static/**","anon");
        //配置系统受限资源
        filterMap.put("/user/**", "authc");
        filterMap.put("/**", "authc");           //主要这行代码必须放在所有权限设置的最后，不然会导致所有 url 都被拦截 剩余的都需要认证
        bean.setFilterChainDefinitionMap(filterMap);

        bean.setLoginUrl("/login");            //没有权限就跳转登录页
        //bean.setSuccessUrl("/index");         //登录成功后跳转(不起效)

        return bean;

    }

    /**
     * 创建安全管理器
     * @param myShiroRealm  下面定义的customRealm（）交给spring管理后，可在参数里直接获取
     * @return
     */
    @Bean
    public DefaultWebSecurityManager securityManager(MyShiroRealm myShiroRealm) {
        DefaultWebSecurityManager defaultSecurityManager = new DefaultWebSecurityManager();
        //给安全管理器设置realm
        defaultSecurityManager.setRealm(myShiroRealm);
        return defaultSecurityManager;
    }

    /**
     * 创建自定义的realm
     * bean名字默认是方法名
     * 登录时的md5解码
     * @return
     */
    @Bean
    public MyShiroRealm customRealm() {
        MyShiroRealm myShiroRealm = new MyShiroRealm();
        //修改凭证校验匹配器
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        //设置加密算法MD5
        credentialsMatcher.setHashAlgorithmName("MD5");
        //设置散列次数
        credentialsMatcher.setHashIterations(1024);
        myShiroRealm.setCredentialsMatcher(credentialsMatcher);

        //开启缓存管理(本地缓存Ehcache)
        myShiroRealm.setCacheManager(new RedisCacheManger());
        myShiroRealm.setCachingEnabled(true);  //开启缓存
        myShiroRealm.setAuthenticationCachingEnabled(true);  //开启认证缓存
        myShiroRealm.setAuthenticationCacheName("authentication");
        myShiroRealm.setAuthorizationCachingEnabled(true);  //开启授权缓存
        myShiroRealm.setAuthorizationCacheName("authorization");
        return myShiroRealm;
    }


    @Bean
    public CookieRememberMeManager cookieRememberMeManager() {
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
        simpleCookie.setMaxAge(259200000);
        cookieRememberMeManager.setCookie(simpleCookie);
        cookieRememberMeManager.setCipherKey(Base64.decode("6ZmI6I2j5Y+R5aSn5ZOlAA=="));
        return cookieRememberMeManager;
    }
}

