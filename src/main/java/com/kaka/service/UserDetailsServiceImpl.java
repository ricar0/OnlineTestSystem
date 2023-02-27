package com.kaka.service;

import com.kaka.entity.LoginUser;
import com.kaka.mapper.UserMapper;
import com.kaka.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // 查询用户信息
        User user = userMapper.listUserByName(username);
        System.out.println(username);
        if (Objects.isNull(user)) { // 查询不到
            throw new RuntimeException("用户名或者密码错误");
        }

        //TODO 查询对应的权限信息

        //把数据封装成UserDetails返回
        return new LoginUser(user);
    }
}
