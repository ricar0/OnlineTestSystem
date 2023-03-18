package com.kaka.service;

import com.kaka.entity.Problem;
<<<<<<< HEAD
import com.kaka.entity.ProblemFilter;
=======
>>>>>>> d05ed3986ccc16b970daa509d36c355f343d6d85

import java.util.List;

public interface ProblemService {
    public List<Problem> getAll();

<<<<<<< HEAD
    public List<Problem> getProblemByFilter(ProblemFilter problemFilter);
=======
    public List<Problem> getProblemByFilter1(String source, String difficulty);
    public List<Problem> getProblemByFilter2(String difficulty);
    public List<Problem> getProblemByFilter3(String source);
>>>>>>> d05ed3986ccc16b970daa509d36c355f343d6d85
}
