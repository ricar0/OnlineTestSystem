package com.kaka.service;

import com.kaka.entity.User;
import com.kaka.utils.ResponseResult;

public interface LoginService {

    ResponseResult login(User user);

    ResponseResult logout();
}
