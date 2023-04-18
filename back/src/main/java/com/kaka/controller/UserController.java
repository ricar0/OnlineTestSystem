package com.kaka.controller;

import com.kaka.entity.User;
import com.kaka.service.UserService;
import com.kaka.utils.RedisCache;
import com.kaka.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
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
    private RedisCache redisCache;

    @Autowired
    private AuthenticationManager authenticationManager;

    @RequestMapping(value="/getAll", method=RequestMethod.GET)
    public ResponseResult getAll() {
        ResponseResult rest = new ResponseResult(200,"查询成功!",userService.listUser());
        return rest;
    }

    @RequestMapping(value="/getListByName", method=RequestMethod.POST)
    public ResponseResult getListByName(@PathParam("username") String Name) {
        User user = userService.findUserByName(Name);
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

    @RequestMapping(value="/getStudentAll", method = RequestMethod.GET)
    public ResponseResult getStudentAll() {
        return new ResponseResult(200, "查询成功!", userService.getStudentAll());
    }

    @RequestMapping(value="/getTeacherAll", method = RequestMethod.GET)
    public ResponseResult getTeacherAll() {
        return new ResponseResult(200, "查询成功!", userService.getTeacherAll());
    }

    @RequestMapping(value="/getUserInfoById/{id}", method = RequestMethod.GET)
    public ResponseResult getUserInfoById(@PathVariable Long id) {
        return new ResponseResult(200, "查询成功!", userService.getUserInfoById(id));
    }

    @RequestMapping(value="/updateUserInfo", method = RequestMethod.POST)
    public ResponseResult updateUserInfo(@RequestBody User user) {
        User user1 = userService.findUserByName(user.getUsername());
        if (!Objects.isNull(user1) && !user1.getId().equals(user.getId())) {
            return new ResponseResult(400, "该用户名已被使用!");
        }
        user1 = userService.findUserByPhone(user.getPhone());
        if (!Objects.isNull(user1) && !user1.getId().equals(user.getId())) {
            return new ResponseResult(400, "该手机号已被使用!");
        }
        user1 = userService.findUserByEmail(user.getEmail());
        if (!Objects.isNull(user1) && !user1.getId().equals(user.getId())) {
            return new ResponseResult(400, "该邮箱已被使用!");
        }
        userService.updateUserInfo(user);
        return new ResponseResult(200, "修改成功!");
    }

    @RequestMapping(value="/updatePassword", method = RequestMethod.POST)
    public ResponseResult updatePassword(@RequestBody User user) {
        userService.updatePassword(user);
        return new ResponseResult(200, "修改成功!");
    }

//    @RequestMapping(value="/resetPassword", method = RequestMethod.POST)
//    public ResponseResult resetPassword(@RequestBody JSONObject jsonObject) {
//        String phoneNumber = jsonObject.getString("phone");
//        String email = jsonObject.getString("email");
//        if (Objects.isNull(phoneNumber)) {
//
//        }
//    }
}
