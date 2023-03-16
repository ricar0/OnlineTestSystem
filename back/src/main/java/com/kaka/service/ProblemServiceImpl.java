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
}
