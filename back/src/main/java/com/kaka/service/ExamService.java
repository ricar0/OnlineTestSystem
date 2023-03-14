package com.kaka.service;

import com.kaka.entity.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface ExamService {
    public List<Exam> getAll();

    public void addExam(Exam exam);

    public List<Exam> getMyExam(HttpServletRequest request);

    public Exam getExamById(Long id);

    public List<Problem> getProblemById(Long id);

    public void startExam(MyExam myExam);

    public void endExam(MyExam myExam);

}
