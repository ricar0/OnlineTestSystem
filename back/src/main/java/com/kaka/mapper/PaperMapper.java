package com.kaka.mapper;

import com.kaka.entity.PaperBean;
import com.kaka.entity.ProblemToPaper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface PaperMapper {
    public void addPaper(ProblemToPaper problemToPaper);
}
