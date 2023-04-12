package com.kaka.service.Impl;

import com.kaka.entity.Exam;
import com.kaka.entity.Search;
import com.kaka.mapper.SearchMapper;
import com.kaka.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    SearchMapper searchMapper;

    @Override
    public List<Exam> searchMyExamByKey(Search search) {
        return searchMapper.searchMyExamByKey(search);
    }
}
