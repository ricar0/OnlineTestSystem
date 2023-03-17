package com.kaka.service;

import com.kaka.entity.Problem;

import java.util.List;

public interface ProblemService {
    public List<Problem> getAll();

    public List<Problem> getProblemByFilter1(String source, String difficulty);
    public List<Problem> getProblemByFilter2(String difficulty);
    public List<Problem> getProblemByFilter3(String source);
}
