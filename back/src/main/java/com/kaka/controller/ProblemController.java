package com.kaka.controller;

import com.kaka.entity.Problem;
import com.kaka.entity.ProblemFilter;
import com.kaka.mapper.ProblemMapper;
import com.kaka.service.ProblemService;
import com.kaka.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/problem")
public class ProblemController {
    @Autowired
    private ProblemService problemService;
    @RequestMapping(value="/getAll", method = RequestMethod.GET)
    ResponseResult getAll() {
        return new ResponseResult(200, "获取成功!", problemService.getAll());
    }

    @RequestMapping(value="/getProblemByFilter", method = RequestMethod.POST)
    ResponseResult getProblemByFilter(@RequestBody ProblemFilter problemFilter) {
        System.out.println(problemFilter);
        return new ResponseResult(200, "获取成功!", problemService.getProblemByFilter(problemFilter));
    }
    @RequestMapping(value="/getAllNumber", method = RequestMethod.GET)
    ResponseResult getAllNumber() {
        return new ResponseResult(200, "获取成功!", problemService.getAllNumber());
    }
    @RequestMapping(value="/getProblemById", method = RequestMethod.POST)
    ResponseResult getProblemById(@RequestBody Problem problem) {
        return new ResponseResult(200, "获取成功!", problemService.getProblemById(problem.getId()));
    }
    @RequestMapping(value="/accept", method = RequestMethod.POST)
    ResponseResult accept(@RequestBody Problem problem) {
        problemService.accept(problem.getId());
        return new ResponseResult(200, "操作成功");
    }
    @RequestMapping(value="/wrongAnswer", method = RequestMethod.POST)
    ResponseResult wrongAnswer(@RequestBody Problem problem) {
        problemService.wrongAnswer(problem.getId());
        return new ResponseResult(200, "操作成功");
    }
}
