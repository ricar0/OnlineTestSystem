package com.kaka.mapper;

import com.kaka.entity.Subject;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SubjectMapper {
    public List<String> getSubjectAll();

    void addSubject(Subject subject);

    Subject findSubjectBySource(String source);

    void deleteSubject(String source);
}
