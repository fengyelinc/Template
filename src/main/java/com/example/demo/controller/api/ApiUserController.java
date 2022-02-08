package com.example.demo.controller.api;

import com.example.demo.annotation.UserLoginToken;
import com.example.demo.base.RestResponse;
import com.example.demo.base.Result;
import com.example.demo.entity.User;
import com.example.demo.entity.VO.UserVO;
import com.example.demo.service.UserService;
import com.example.demo.utils.JWTHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@Api
@Slf4j
@RequestMapping("api/user")
public class ApiUserController {
    @Autowired
    private UserService userService;

    @ApiOperation(value = "获取用户信息")
    @PostMapping("userInfo")
    @UserLoginToken
    @ResponseBody
    public Result getUserInfo(HttpServletRequest request) {
//        RestResponse res = new RestResponse();
        User user = null;
        try {
            user = JWTHelper.getUserInfo(request.getHeader("token"));
        } catch (Exception e) {
            log.error("系统异常", e);
        }
        UserVO userVO = new UserVO();
        User user1 = userService.selectUserByAccount(user.getAccount());
        BeanUtils.copyProperties(user1, userVO);
        return Result.success(userVO);
    }
}
