package com.kaka;

import com.kaka.entity.User;
import com.kaka.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;


import javax.sql.DataSource;

@SpringBootTest
class SecurityApplicationTests {

    @Autowired
    private UserService userService;

    @Autowired(required = false)
    private DataSource dataSource;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test // 测试数据库连接
    void dataSource() throws Exception {
        System.out.println(dataSource.getConnection());
    }

    @Test
    void getAll() {
        System.out.println(userService.listUser());
    }

    @Test
    void addUser() {
        User user = new User();
        user.setUsername("root");
        user.setPassword("123123");
        userService.addUser(user);
    }

}
