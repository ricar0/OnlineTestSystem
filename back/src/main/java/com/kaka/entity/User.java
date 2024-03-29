package com.kaka.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


/**
 * 用户表(User)实体类
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    private static final long serialVersionUID = -40356785423868312L;

    //主键
    private Long id;
    //用户名
    private String username;
    //密码
    private String password;
    //权限
    private String role;
    //手机
    private String phone;
    //邮箱
    private String email;
    //学校
    private String school;
    //专业
    private String major;
    //身份证
    private String idcard;
    //年级
    private String grade;
    //性别
    private String sex;
    //地区
    private String region;
    //生日
    private String birthday;

}