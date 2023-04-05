package com.kaka.mapper;

import com.kaka.entity.WrongBook;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface WrongBookMapper {
    public void addProblemToWrongBook(WrongBook wrongBook);

    public WrongBook findWrongProblem(WrongBook wrongBook);

    public List<WrongBook> getWrongBookByUserId(Long user_id);

    public void deleteWrongProblem(WrongBook wrongBook);

    public Integer getWrongProblemNumber(Long user_id);
}
