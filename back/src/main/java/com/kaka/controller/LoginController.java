package com.kaka.controller;

import com.kaka.entity.PhoneCheck;
import com.kaka.entity.User;
import com.kaka.service.LoginService;
import com.kaka.utils.RedisCache;
import com.kaka.utils.ResponseResult;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;

@RestController
@RequestMapping("/api/auth")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @RequestMapping(value="/login", method = RequestMethod.POST)
    public ResponseResult login(@RequestBody User user) {
        return loginService.login(user);
    }

    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public ResponseResult logout() {
        return loginService.logout();
    }

    //根据token获取用户信息
    @RequestMapping(value="/getLoginInfo", method = RequestMethod.GET)
    public ResponseResult getLoginInfo(HttpServletRequest request) { ;
        return loginService.getLoginInfo(request);
    }

    @RequestMapping(value="/sendCode", method = RequestMethod.POST)
    public ResponseResult sendCode(@RequestBody User user) {
        return loginService.sendCode(user);
    }

    @RequestMapping(value="/getCode", method = RequestMethod.POST)
    public ResponseResult getCode(@RequestBody PhoneCheck phoneCheck) {
        return loginService.getCode(phoneCheck);
    }

}
