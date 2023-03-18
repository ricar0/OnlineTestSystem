package com.kaka.service;

import com.kaka.entity.Exam;
import com.kaka.entity.Search;

import java.util.List;

public interface SearchService {
    public List<Exam> searchMyExamByKey(Search search);
}
