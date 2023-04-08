package com.kaka.mapper;

import com.kaka.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper  // 告诉springboot这是一个mybatis的mapper类
@Repository // 将UserMapper交由spring容器管理
public interface UserMapper {
    //查询所有用户
    public List<User> listUser();

    //根据用户名查询用户
    public User findUserByName(String name);

    //添加用户
    public void addUser(User user);

    //根据用户ID查询权限
    public String findRoleById(Long id);

    //根据用户手机号查询用户信息
    public User findUserByPhone(String phone);

    // 获取所有学生
    public List<User> getStudentAll();

    //根据id查询学生信息
    public User getUserInfoById(Long id);

    //修改学生信息
    void updateUserInfo(User user);

    public User findUserByEmail(String email);

    void updatePassword(User user);

    void updateEmail(User user);
}
