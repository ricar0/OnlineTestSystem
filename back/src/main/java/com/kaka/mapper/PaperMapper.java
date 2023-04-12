package com.kaka.mapper;

import com.kaka.entity.Exam;
import com.kaka.entity.PaperBean;
import com.kaka.entity.ProblemToPaper;
import com.kaka.entity.RuleBean;
import com.kaka.utils.ResponseResult;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface PaperMapper {
    public void addPaper(ProblemToPaper problemToPaper);

    public ResponseResult addPaperByRand(Exam exam);

    public ResponseResult addExamByGeneticAlgorithm(RuleBean ruleBean);
}
