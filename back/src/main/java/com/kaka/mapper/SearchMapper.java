package com.kaka.mapper;

import com.kaka.entity.Exam;
import com.kaka.entity.Search;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface SearchMapper {
    public List<Exam> searchMyExamByKey(Search search);
}
