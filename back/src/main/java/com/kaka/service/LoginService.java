package com.kaka.service;

import com.kaka.entity.User;
import com.kaka.utils.ResponseResult;

import javax.servlet.http.HttpServletRequest;

public interface LoginService {

    ResponseResult login(User user);

    ResponseResult logout();

    ResponseResult getLoginInfo(HttpServletRequest request);
}
