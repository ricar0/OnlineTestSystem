package com.kaka.service.Impl;

import com.kaka.entity.User;
import com.kaka.mapper.UserMapper;
import com.kaka.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public List<User> listUser() {
        return userMapper.listUser();
    }

    @Override
    public User findUserByName(String name) {
        return userMapper.findUserByName(name);
    }

    @Override
    public void addUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userMapper.addUser(user);
    }

    @Override
    public String findRoleById(Long id) {
        return userMapper.findRoleById(id);
    }

    @Override
    public User findUserByPhone(String phone) {
        return userMapper.findUserByPhone(phone);
    }

    @Override
    public List<User> getStudentAll() {
        return userMapper.getStudentAll();
    }

    @Override
    public User getUserInfoById(Long id) {
        return userMapper.getUserInfoById(id);
    }

    @Override
    public void updateUserInfo(User user) {
        userMapper.updateUserInfo(user);
    }

    @Override
    public User findUserByEmail(String email) {
        return userMapper.findUserByEmail(email);
    }

    @Override
    public void updatePassword(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userMapper.updatePassword(user);
    }

    @Override
    public void updateEmail(User user) {
        userMapper.updateEmail(user);
    }

    @Override
    public List<User> getTeacherAll() {
        return userMapper.getTeacherAll();
    }

    @Override
    public void updateRole(Long id) {
        userMapper.updateRole(id);
    }
}
