package com.kaka.mapper;

import com.kaka.entity.Problem;
import com.kaka.entity.ProblemFilter;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ProblemMapper {
    public List<Problem> getAll();

    public List<Problem> getProblemByFilter(ProblemFilter problemFilter);

    public Integer getAllNumber();

    public Problem getProblemById(Long id);

    void accept(Long id);

    void wrongAnswer(Long id);
}
