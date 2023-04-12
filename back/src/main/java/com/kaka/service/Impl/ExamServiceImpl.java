package com.kaka.service.Impl;

import com.kaka.entity.*;
import com.kaka.mapper.ExamMapper;
import com.kaka.service.ExamService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.kaka.utils.JwtUtil.parseJWT;

@Service
public class ExamServiceImpl implements ExamService {

    @Autowired
    private ExamMapper examMapper;

    @Override
    public List<Exam> getAll() {
        return examMapper.getAll();
    }

    @Override
    public Long addExam(Exam exam) {
        examMapper.addExam(exam);
        return exam.getId();
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

    @Override
    public void deleteExam(Long id) {
        examMapper.deleteExam(id);
    }

    @Override
    public void deleteExamProblem(Long id) {
        examMapper.deleteExamProblem(id);
    }

    @Override
    public void updateExamInfo(Exam exam) {
        examMapper.updateExamInfo(exam);
    }

}
