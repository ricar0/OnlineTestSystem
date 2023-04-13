package com.kaka.service.Impl;

import com.kaka.mapper.SubjectMapper;
import com.kaka.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectServiceImpl implements SubjectService {

    @Autowired
    private SubjectMapper subjectMapper;

    @Override
    public List<String> getSubjectAll() {
        return subjectMapper.getSubjectAll();
    }
}
