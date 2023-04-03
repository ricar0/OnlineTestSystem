package com.kaka.service;

import com.kaka.entity.*;
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
    public List<Exam> getMyExam(MyExamFilter myExamFilter) {
        return examMapper.getMyExam(myExamFilter);
    }

    @Override
    public Exam getExamById(Long id) {
        return examMapper.getExamById(id);
    }

    @Override
    public List<Problem> getProblemById(Long id) {
        return examMapper.getProblemById(id);
    }

    @Override
    public void startExam(MyExam myExam) {
        examMapper.startExam(myExam);
    }

    @Override
    public void endExam(MyExam myExam) {
        examMapper.endExam(myExam);
    }

    @Override
    public List<Exam> getExamByFilter(ExamFilter examFilter) {
        return examMapper.getExamByFilter(examFilter);
    }

    @Override
    public Integer getUserNumberByExamId(Long id) {
        return examMapper.getUserNumberByExamId(id);
    }

}
