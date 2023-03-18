package com.kaka.service;

import com.kaka.entity.Practice;
import com.kaka.mapper.PracticeMapper;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.kaka.utils.JwtUtil.parseJWT;

@Service
public class PracticeServiceImpl implements PracticeService{

    @Autowired
    private PracticeMapper practiceMapper;

    @Override
    public void addPractice(Practice practice) {
        practiceMapper.addPractice(practice);
    }

    @Override
    public List<Practice> getMyPractice(HttpServletRequest request) {
        String token = request.getHeader("token");
        //解析token
        String user_id;
        try {
            Claims claims = parseJWT(token);
            user_id = claims.getSubject();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("tolen非法");
        }
        Long userid= Long.valueOf(user_id);
        return practiceMapper.getMyPractice(userid);
    }
}
