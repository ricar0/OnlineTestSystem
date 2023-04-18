package com.kaka.mapper;

import com.kaka.entity.*;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ExamMapper {
    public List<Exam> getAll();

    public Long addExam(Exam exam);

    public List<Exam> getMyExam(MyExamFilter myExamFilter);

    public Exam getExamById(Long id);

    public List<Problem> getProblemById(Long id);

    public void startExam(MyExam myExam);

    public MyExam getExamInfo(MyExam myExam);

    public void endExam(MyExam myExam);

    public List<Exam> getExamByFilter(ExamFilter examFilter);

    public Integer getUserNumberByExamId(Long id);

    public void deleteExam(Long id);

    public void deleteExamProblem(Long id);

    public void updateExamInfo(Exam exam);

    public void registerExam(MyExam myExam);

    public MyExam getRegisterState(MyExam myExam);

    public void addExamNumber(Long id);

    List<ScoreResult> getRecentExamResult(Long id);

    List<MyExam> getExamScoreResult(Long id);

    List<ScoreResult> getRecentExamScore();
}
