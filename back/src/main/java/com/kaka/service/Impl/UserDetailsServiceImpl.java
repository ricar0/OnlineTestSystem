package com.kaka.service.Impl;

import com.kaka.entity.LoginUser;
import com.kaka.mapper.UserMapper;
import com.kaka.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // 查询用户信息
        User user = userMapper.findUserByName(username);
        if (Objects.isNull(user)) { // 查询不到
            throw new RuntimeException("用户名或者密码错误");
        }
        //TODO 查询对应的权限信息
        List<String> list = new ArrayList<>();
        list.add(userMapper.findRoleById(user.getId()));
        //把数据封装成UserDetails返回
        return new LoginUser(user, list);
    }
}
