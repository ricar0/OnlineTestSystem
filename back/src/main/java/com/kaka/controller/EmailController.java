package com.kaka.controller;

import com.kaka.entity.EmailCheck;
import com.kaka.entity.User;
import com.kaka.mapper.UserMapper;
import com.kaka.service.EmailService;
import com.kaka.utils.EmailUtils;
import com.kaka.utils.RedisCache;
import com.kaka.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api/email")
public class EmailController {


    private final EmailService emailService;

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private UserMapper userMapper;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @RequestMapping(value="/sendEmail", method = RequestMethod.POST)
    public ResponseResult sendEmail(@RequestBody User user) throws MessagingException {
        // 生成随机验证码
        String code = EmailUtils.generateVerificationCode();
        redisCache.setCacheObject("userid_email:"+user.getId(), code, 60*10, TimeUnit.SECONDS);
        // 发送验证码邮件
        emailService.sendSimpleMessage(user.getEmail(), "验证码通知", "【Exam-Online】验证码:"+code);
        return new ResponseResult(200, "发送成功!");
    }

    @RequestMapping(value="/verifyEmail", method = RequestMethod.POST)
    public ResponseResult verifyEmail(@RequestBody EmailCheck emailCheck) {
        String code = redisCache.getCacheObject("userid_email:"+emailCheck.getId());
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

}
