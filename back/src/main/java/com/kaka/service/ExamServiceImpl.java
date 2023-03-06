package com.kaka.service;

import com.kaka.entity.Exam;
import com.kaka.mapper.ExamMapper;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.kaka.utils.JwtUtil.parseJWT;

@Service
public class ExamServiceImpl implements ExamService{

    @Autowired
    private ExamMapper examMapper;

    @Override
    public List<Exam> getAll() {
        return examMapper.getAll();
    }

    @Override
    public void addExam(Exam exam) {
        examMapper.addExam(exam);
    }

    @Override
    public List<Exam> getMyExam(HttpServletRequest request) {
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
        return examMapper.getMyExam(userid);
    }
}
