package com.kaka.mapper;

import com.kaka.entity.Problem;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ProblemMapper {
    public List<Problem> getAll();

    public List<Problem> getProblemByFilter1(String source, String difficulty);
    public List<Problem> getProblemByFilter2(String difficulty);
    public List<Problem> getProblemByFilter3(String source);
}
