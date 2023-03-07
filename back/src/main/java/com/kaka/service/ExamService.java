package com.kaka.service;

import com.kaka.entity.Exam;
import com.kaka.entity.Problem;
import com.kaka.utils.ResponseResult;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface ExamService {
    public List<Exam> getAll();

    public void addExam(Exam exam);

    public List<Exam> getMyExam(HttpServletRequest request);

    public Exam getExamById(Long id);

    public List<Problem> getProblemById(Long id);

}
