package com.kaka.service;

import com.kaka.entity.Problem;
import com.kaka.entity.ProblemFilter;
import com.kaka.mapper.ProblemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProblemServiceImpl implements ProblemService{

    @Autowired
    private ProblemMapper problemMapper;
    @Override
    public List<Problem> getAll() {
        return problemMapper.getAll();
    }

    @Override
    public List<Problem> getProblemByFilter(ProblemFilter problemFilter) {
        return problemMapper.getProblemByFilter(problemFilter);
    }

    @Override
    public Integer getAllNumber() {
        return problemMapper.getAllNumber();
    }

    @Override
    public Problem getProblemById(Long id) {
        return problemMapper.getProblemById(id);
    }

    @Override
    public void accept(Long id) {
        problemMapper.accept(id);
    }

    @Override
    public void wrongAnswer(Long id) {
        problemMapper.wrongAnswer(id);
    }

}
