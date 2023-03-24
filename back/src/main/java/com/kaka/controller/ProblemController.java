package com.kaka.controller;

import com.kaka.entity.Problem;
import com.kaka.entity.ProblemFilter;
import com.kaka.service.ProblemService;
import com.kaka.utils.RedisCache;
import com.kaka.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/problem")
public class ProblemController {

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private ProblemService problemService;
    @RequestMapping(value="/getAll", method = RequestMethod.GET)
    ResponseResult getAll() {
        return new ResponseResult(200, "获取成功!", problemService.getAll());
    }

    @RequestMapping(value="/getProblemByFilter", method = RequestMethod.POST)
    ResponseResult getProblemByFilter(@RequestBody ProblemFilter problemFilter) {
        return new ResponseResult(200, "获取成功!", problemService.getProblemByFilter(problemFilter));
    }
    @RequestMapping(value="/getAllNumber", method = RequestMethod.POST)
    ResponseResult getAllNumber(@RequestBody ProblemFilter problemFilter) {
        List<Problem> list = problemService.getProblemByFilter(problemFilter);
        return new ResponseResult(200, "获取成功!", list.size());
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
