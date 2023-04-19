package com.kaka.service;

import com.kaka.entity.*;

import java.util.List;

public interface ExamService {
    public List<Exam> getAll();

    public Long addExam(Exam exam);

    public List<Exam> getMyExam(MyExamFilter myExamFilter);

    public Exam getExamById(Long id);

    public List<Problem> getProblemById(Long id);

    public void startExam(MyExam myExam);

    public void endExam(MyExam myExam);

    public List<Exam> getExamByFilter(ExamFilter examFilter);

    public Integer getUserNumberByExamId(Long id);

    public void deleteExam(Long id);

    public void deleteExamProblem(Long id);

    public void updateExamInfo(Exam exam);

    void registerExam(MyExam myExam);

    void initExam(Exam exam, Long id);

    MyExam getRegisterState(MyExam myExam);

    List<ScoreResult> getRecentExamResult(Long id);

    List<MyExam> getExamScoreResult(Long id);

    List<ScoreResult> getRecentExamScore();

    List<MyExam> getExamRank(Long id);
}
