package com.kaka.controller;

import com.kaka.entity.User;
import com.kaka.service.LoginService;
import com.kaka.service.UserService;
import com.kaka.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.Objects;


@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private LoginService loginService;

    @RequestMapping(value="/getAll", method=RequestMethod.GET)
    public ResponseResult getAll() {
        ResponseResult rest = new ResponseResult(200,"查询成功!",userService.listUser());
        return rest;
    }

    @RequestMapping(value="/getListByName", method=RequestMethod.POST)
    public ResponseResult getListByName(@PathParam("username") String Name) {
        User user = userService.listUserByName(Name);
        ResponseResult rest;
        if (!Objects.isNull(user)) {
            rest = new ResponseResult(200, "查询成功!", user);
        } else {
            rest = new ResponseResult(400, "未找到", null);
        }
        return rest;
    }

    @RequestMapping(value="/addUser", method = RequestMethod.POST)
    public ResponseResult addUser(@RequestBody User user) {
        userService.addUser(user);
        ResponseResult rest = new ResponseResult(200, "插入成功!", null);
        return rest;
    }

    @RequestMapping(value="/login", method = RequestMethod.POST)
    public ResponseResult login(@RequestBody User user) {
        return loginService.login(user);
    }

    @RequestMapping(value="/logout", method = RequestMethod.POST)
    public ResponseResult logout() {
        return loginService.logout();
    }
}
