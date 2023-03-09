package com.kaka.mapper;

import com.kaka.entity.Exam;
import com.kaka.entity.MyExam;
import com.kaka.entity.Problem;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Mapper
@Repository
public interface ExamMapper {
    public List<Exam> getAll();

    public void addExam(Exam exam);

    public List<Exam> getMyExam(Long user_id);

    public Exam getExamById(Long id);

    public List<Problem> getProblemById(Long id);

    public void startExam(MyExam myExam);

    public MyExam getExamInfo(MyExam myExam);

    public void endExam(MyExam myExam);
}
