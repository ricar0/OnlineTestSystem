package com.kaka.service;

import com.kaka.entity.WrongBook;

import java.util.List;

public interface WrongBookService {
    public void addProblemToWrongBook(WrongBook wrongBook);

    public WrongBook findWrongProblem(WrongBook wrongBook);

    public List<WrongBook> getWrongBookByUserId(Long user_id);

    public void deleteWrongProblem(WrongBook wrongBook);

    public Integer getWrongProblemNumber(Long user_id);
}
