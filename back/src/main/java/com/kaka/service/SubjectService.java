package com.kaka.service;

import com.kaka.entity.Subject;

import java.util.List;

public interface SubjectService {

    public List<String> getSubjectAll();

    void addSubject(Subject subject);

    Subject findSubjectBySource(String source);

    void deleteSubject(String source);
}
