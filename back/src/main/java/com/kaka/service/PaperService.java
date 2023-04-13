package com.kaka.service;

import com.kaka.entity.Exam;
import com.kaka.entity.PaperBean;
import com.kaka.entity.RuleBean;
import com.kaka.utils.ResponseResult;

import java.util.concurrent.Future;

public interface PaperService {
    public void addPaper(PaperBean paperBean);

    public ResponseResult addPaperByRand(Exam exam);

    public Future<ResponseResult> addExamByGeneticAlgorithm(RuleBean ruleBean);
}
