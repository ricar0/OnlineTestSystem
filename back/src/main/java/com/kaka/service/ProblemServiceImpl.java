package com.kaka.service;

import com.kaka.entity.Problem;
<<<<<<< HEAD
import com.kaka.entity.ProblemFilter;
=======
>>>>>>> d05ed3986ccc16b970daa509d36c355f343d6d85
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
<<<<<<< HEAD
    public List<Problem> getProblemByFilter(ProblemFilter problemFilter) {
        return problemMapper.getProblemByFilter(problemFilter);
    }

=======
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
>>>>>>> d05ed3986ccc16b970daa509d36c355f343d6d85

}
