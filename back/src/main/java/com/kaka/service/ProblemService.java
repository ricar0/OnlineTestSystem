package com.kaka.service;

import com.kaka.entity.Problem;
import com.kaka.entity.ProblemFilter;

import java.util.List;

public interface ProblemService {
    public List<Problem> getAll();

    public List<Problem> getProblemByFilter(ProblemFilter problemFilter);
}
