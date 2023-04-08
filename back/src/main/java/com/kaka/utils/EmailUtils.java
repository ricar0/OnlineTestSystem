package com.kaka.utils;

import java.util.Random;

public class EmailUtils {
    public static String generateVerificationCode() {
        // 生成随机验证码的代码
        String code = generateRandomCode(6);
        return code;
    }
    public static String generateRandomCode(int length) {
        String code = "";
        String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(characters.length());
            char randomChar = characters.charAt(randomIndex);
            code += randomChar;
        }
        return code;
    }
}
