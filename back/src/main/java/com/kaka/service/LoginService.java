package com.kaka.service;

import com.kaka.entity.PhoneCheck;
import com.kaka.entity.User;
import com.kaka.utils.ResponseResult;

import javax.servlet.http.HttpServletRequest;

public interface LoginService {

    ResponseResult login(User user);

    ResponseResult logout();

    ResponseResult getLoginInfo(HttpServletRequest request);

    ResponseResult sendCode(User user);

    ResponseResult getCode(PhoneCheck phoneCheck);
}
