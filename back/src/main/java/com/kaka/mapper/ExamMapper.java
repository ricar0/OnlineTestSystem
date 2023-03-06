package com.kaka.mapper;

import com.kaka.entity.Exam;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Mapper
@Repository
public interface ExamMapper {
    public List<Exam> getAll();

    public void addExam(Exam exam);

    public List<Exam> getMyExam(Long user_id);
}
