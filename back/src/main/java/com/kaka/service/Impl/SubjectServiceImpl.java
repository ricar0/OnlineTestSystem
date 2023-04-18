package com.kaka.service.Impl;

import com.kaka.entity.Subject;
import com.kaka.mapper.SubjectMapper;
import com.kaka.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Override
    public void addSubject(Subject subject) {
        subjectMapper.addSubject(subject);
    }

    @Override
    public Subject findSubjectBySource(String source) {
        return subjectMapper.findSubjectBySource(source);
    }

    @Override
    public void deleteSubject(String source) {
        subjectMapper.deleteSubject(source);
    }
}
