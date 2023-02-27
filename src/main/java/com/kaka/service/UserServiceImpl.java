package com.kaka.service;

import com.kaka.entity.User;
import com.kaka.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public List<User> listUser() {
        return userMapper.listUser();
    }

    @Override
    public User listUserByName(String name) {
        return userMapper.listUserByName(name);
    }

    @Override
    public void addUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userMapper.addUser(user);
    }
}
