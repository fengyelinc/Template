package com.example.demo.controller;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.example.demo.base.RestResponse;
import com.example.demo.config.shiro.realm.CustomToken;
import com.example.demo.config.shiro.realm.LoginType;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
public class LoginController {


    @GetMapping("login")
    public String login(){
        return "login";
    }

    @PostMapping("login/main")
    @ResponseBody
    public RestResponse loginMain(HttpServletRequest request) {
        String account = request.getParameter("account");
        String password = request.getParameter("password");
        String rememberMe = request.getParameter("rememberMe");
        if(StringUtils.isBlank(account) || StringUtils.isBlank(password)){
            return RestResponse.failure("账号或者密码不能为空");
        }
        if(StringUtils.isBlank(rememberMe)){
            return RestResponse.failure("记住我不能为空");
        }
        Map<String,Object> map = new HashMap<>();
        String error = null;
        HttpSession session = request.getSession();
        if(session == null){
            return RestResponse.failure("session超时");
        }

        /*就是代表当前的用户。*/
        Subject user = SecurityUtils.getSubject();
        //这里的token可以被shiro MyShiroRealm 中的doGetAuthenticationInfo拿到
        CustomToken token = new CustomToken(account,password, LoginType.PASSWORD,Boolean.valueOf(rememberMe));    //先省略remember
        try {
            user.login(token);                      //shiro登录核心方法
            if (user.isAuthenticated()) {
                map.put("url","index");        //登陆成功跳转首页
            }
        }catch (IncorrectCredentialsException e) {
            error = "登录密码错误.";
        } catch (ExcessiveAttemptsException e) {
            error = "登录失败次数过多";
        } catch (LockedAccountException e) {
            error = "帐号已被锁定.";
        } catch (DisabledAccountException e) {
            error = "帐号已被禁用.";
        } catch (ExpiredCredentialsException e) {
            error = "帐号已过期.";
        } catch (UnknownAccountException e) {
            error = "帐号不存在";
        } catch (UnauthorizedException e) {
            error = "您没有得到相应的授权！";
        }
        if(StringUtils.isBlank(error)){
            return RestResponse.success("登录成功").setData(map);
        }else{
            return RestResponse.failure(error);
        }
    }

    @GetMapping("index")
    public String index(Model model){
        return "index";
    }

    /**
     *  空地址请求
     * @return
     */
    @GetMapping(value = "")
    public String index() {
//        LOGGER.info("这事空地址在请求路径");
        Subject s = SecurityUtils.getSubject();
        return s.isAuthenticated() ? "redirect:/index" : "/login";
    }
}
