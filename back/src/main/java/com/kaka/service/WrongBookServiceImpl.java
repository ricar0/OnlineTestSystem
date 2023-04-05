package com.kaka.service;


import com.kaka.entity.WrongBook;
import com.kaka.mapper.WrongBookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WrongBookServiceImpl implements WrongBookService{

    @Autowired
    private WrongBookMapper wrongBookMapper;
    @Override
    public void addProblemToWrongBook(WrongBook wrongBook) {
        wrongBookMapper.addProblemToWrongBook(wrongBook);
    }

    @Override
    public WrongBook findWrongProblem(WrongBook wrongBook) {
        return wrongBookMapper.findWrongProblem(wrongBook);
    }

    @Override
    public List<WrongBook> getWrongBookByUserId(Long user_id) {
        return wrongBookMapper.getWrongBookByUserId(user_id);
    }

    @Override
    public void deleteWrongProblem(WrongBook wrongBook) {
        wrongBookMapper.deleteWrongProblem(wrongBook);
    }

    @Override
    public Integer getWrongProblemNumber(Long user_id) {
        return wrongBookMapper.getWrongProblemNumber(user_id);
    }
}
