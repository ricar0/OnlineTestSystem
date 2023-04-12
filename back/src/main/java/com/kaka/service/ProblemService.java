package com.kaka.service;

import com.kaka.entity.Problem;
import com.kaka.entity.ProblemFilter;
import com.kaka.utils.ResponseResult;

import java.util.List;

public interface ProblemService {
    public List<Problem> getAll();

    public List<Problem> getProblemByFilter(ProblemFilter problemFilter);

    public Integer getAllNumber();

    public Problem getProblemById(Long id);

    public void accept(Long id);

    public void wrongAnswer(Long id);

    public void addProblem(Problem problem);
}
