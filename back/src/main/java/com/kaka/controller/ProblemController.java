package com.kaka.controller;

import com.kaka.mapper.ProblemMapper;
import com.kaka.service.ProblemService;
import com.kaka.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/problem")
public class ProblemController {
    @Autowired
    private ProblemService problemService;
    @RequestMapping(value="/getAll", method = RequestMethod.GET)
    ResponseResult getAll() {
        return new ResponseResult(200, "获取成功!", problemService.getAll());
    }
}
