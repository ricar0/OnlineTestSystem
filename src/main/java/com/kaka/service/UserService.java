package com.kaka.service;

import com.kaka.entity.User;

import java.util.List;

public interface UserService {
    //查询所有用户
    public List<User> listUser();

    //根据用户名查询用户
    public User findUserByName(String name);

    //添加用户
    public void addUser(User user);

    //根据用户ID查询权限
    public String findRoleById(Long id);
}
