package com.example.demo.controller;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.example.demo.base.RestResponse;
import com.example.demo.config.shiro.realm.CustomToken;
import com.example.demo.config.shiro.realm.LoginType;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import com.example.demo.utils.Constants;
import com.example.demo.utils.SaltUtils;
import com.example.demo.utils.StringHelper;
import com.example.demo.utils.VerifyCodeUtil;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
public class LoginController {
    @Autowired
    private UserService userService;

    @GetMapping("login")
    public String login() {
        return "login";
    }

    @GetMapping("register")
    public String register() {
        return "register";
    }

    /**
     * 后台页面登录
     *
     * @param request
     * @return
     */
    @PostMapping("login/main")
    @ResponseBody
    public RestResponse loginMain(HttpServletRequest request) {
        /*实际开发加入下面的验证码校验逻辑
         */
//		String code = request.getParameter("code");
        String account = request.getParameter("account");
        String password = request.getParameter("password");
        String rememberMe = request.getParameter("rememberMe");
        if (StringUtils.isBlank(account) || StringUtils.isBlank(password)) {
            return RestResponse.failure("账号或者密码不能为空");
        }
        if (StringUtils.isBlank(rememberMe)) {
            return RestResponse.failure("记住我不能为空");
        }
//		if(StringUtils.isBlank(code)){
//			return  RestResponse.failure("验证码不能为空");
//		}
        Map<String, Object> map = new HashMap<>();
        String error = null;
        HttpSession session = request.getSession();
        if (session == null) {
            return RestResponse.failure("session超时");
        }
//		String trueCode =  (String)session.getAttribute(Constants.VALIDATE_CODE);
//		if(StringUtils.isBlank(trueCode)){
//			return RestResponse.failure("验证码超时");
//		}
//		if(StringUtils.isBlank(code) || !trueCode.toLowerCase().equals(code.toLowerCase())){
//			error = "验证码错误";
//		}else {

        /*就是代表当前的用户。*/
        Subject user = SecurityUtils.getSubject();
        //这里的token可以被shiro MyShiroRealm 中的doGetAuthenticationInfo拿到
        CustomToken token = new CustomToken(account, password, LoginType.PASSWORD, Boolean.valueOf(rememberMe));
        try {
            user.login(token);                      //shiro登录核心方法
            if (user.isAuthenticated()) {
                map.put("url", "index");            //登陆成功跳转首页
            }
        } catch (IncorrectCredentialsException e) {
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
        if (StringUtils.isBlank(error)) {
            return RestResponse.success("登录成功").setData(map);
        } else {
            return RestResponse.failure(error);
        }
    }

    @GetMapping("index")
    public String index(Model model) {
        return "index";
    }

    /**
     * 空地址请求
     *
     * @return
     */
    @GetMapping(value = "")
    public String index() {
//        LOGGER.info("这事空地址在请求路径");
        Subject s = SecurityUtils.getSubject();
        return s.isAuthenticated() ? "redirect:/index" : "/login";
    }

    /**
     * 退出等陆
     *
     * @return
     */
    @GetMapping("logout")
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "login";
    }


    /**
     * 注册
     * @param request
     * @return
     */
    @PostMapping("submit/register")
    @ResponseBody
    public RestResponse submitRegistration(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = new User();
        user.setName(username);
        String uuid6 = "";
        int count = 1;
        //去重
        while (count >= 1) {
            uuid6 = StringHelper.uuid6();
            count = userService.queryAccount(uuid6);
        }
        user.setAccount(uuid6);
        //生成随机盐
        String salt = SaltUtils.getSalt(8);
        //将随机盐保存到数据库
        user.setSalt(salt);
        //将明文密码进行MD5+salt+hash散列
        Md5Hash md5Hash = new Md5Hash(password, salt, 1024);
        user.setPassword(md5Hash.toHex());
        boolean b = userService.save(user);
        Map<String, String> map = new HashMap<>();
        if (b) {
            map.put("url", "login");
            map.put("account", user.getAccount());
        }
        return b ? RestResponse.success("注册成功").setData(map) : RestResponse.failure("注册失败");
    }


    /**
     * 图片验证码
     */
    @GetMapping("/genCaptcha")
    public void genCaptcha(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //设置页面不缓存
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        String verifyCode = VerifyCodeUtil.generateTextCode(VerifyCodeUtil.TYPE_ALL_MIXED, 4, null);
        //将验证码放到HttpSession里面
        request.getSession().setAttribute(Constants.VALIDATE_CODE, verifyCode);
        System.out.println("本次生成的验证码为[" + verifyCode + "],已存放到HttpSession中");        //设置输出的内容的类型为JPEG图像
        response.setContentType("image/jpeg");
        BufferedImage bufferedImage = VerifyCodeUtil.generateImageCode(verifyCode, 116, 36, 5, true, new Color(249, 205, 173), null, null);
        //写给浏览器
        ImageIO.write(bufferedImage, "JPEG", response.getOutputStream());
    }


}
