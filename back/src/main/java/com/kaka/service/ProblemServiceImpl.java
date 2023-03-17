package com.kaka.service;

import com.kaka.entity.Problem;
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
    public List<Problem> getProblemByFilter1(String source, String difficulty) {
        return problemMapper.getProblemByFilter1(source, difficulty);
    }

    @Override
    public List<Problem> getProblemByFilter2(String difficulty) {
        return problemMapper.getProblemByFilter2(difficulty);
    }

    @Override
    public List<Problem> getProblemByFilter3(String source) {
        return problemMapper.getProblemByFilter3(source);
    }

}
