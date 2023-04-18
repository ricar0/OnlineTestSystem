package com.kaka.controller;

import com.alibaba.fastjson.JSONObject;
import com.kaka.entity.EmailCheck;
import com.kaka.entity.User;
import com.kaka.mapper.UserMapper;
import com.kaka.service.Impl.EmailService;
import com.kaka.utils.EmailUtils;
import com.kaka.utils.RedisCache;
import com.kaka.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api/email")
public class EmailController {


    private final EmailService emailService;

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @RequestMapping(value="/sendEmail", method = RequestMethod.POST)
    public ResponseResult sendEmail(@RequestBody User user) throws MessagingException {
        User tmp = userMapper.findUserByEmail(user.getEmail());
        if (Objects.isNull(tmp)) {
            return new ResponseResult(400, "未找到该邮箱!");
        }
        // 生成随机验证码
        String code = EmailUtils.generateVerificationCode();
        redisCache.setCacheObject("userid_email:"+user.getEmail(), code, 60*10, TimeUnit.SECONDS);
        // 发送验证码邮件
        emailService.sendSimpleMessage(user.getEmail(), "验证码通知", "【Exam-Online】验证码:"+code);
        return new ResponseResult(200, "发送成功!");
    }

    @RequestMapping(value="/verifyEmail", method = RequestMethod.POST)
    public ResponseResult verifyEmail(@RequestBody EmailCheck emailCheck) {
        String code = redisCache.getCacheObject("userid_email:"+emailCheck.getEmail());
        if (code.equals(emailCheck.getCode())) {
            User user = new User();
            user.setId(emailCheck.getId());
            user.setEmail(emailCheck.getEmail());
            userMapper.updateEmail(user);
            return new ResponseResult(200, "修改成功!");
        } else {
            return new ResponseResult(400, "验证码错误!");
        }
    }

    @RequestMapping(value="/verifyEmailByCode", method = RequestMethod.POST)
    public ResponseResult verifyEmailByCode(@RequestBody EmailCheck emailCheck) {
        String code = redisCache.getCacheObject("userid_email:"+emailCheck.getEmail());
        if (code.equals(emailCheck.getCode())) {
            return new ResponseResult(200, "修改成功!");
        } else {
            return new ResponseResult(400, "验证码错误!");
        }
    }

    @RequestMapping(value="/updatePasswordByEmail", method = RequestMethod.POST)
    public ResponseResult resetPasswordByMail(@RequestBody JSONObject jsonObject) {
        String password = passwordEncoder.encode(jsonObject.getString("password"));
        User user = new User();
        user.setPassword(password);
        user.setEmail(jsonObject.getString("email"));
        userMapper.updatePasswordByEmail(user);
        return new ResponseResult(200, "修改成功!");
    }

}
