package com.kaka.controller;

import com.kaka.entity.Problem;
import com.kaka.entity.ProblemFilter;
import com.kaka.service.ProblemService;
import com.kaka.utils.RedisCache;
import com.kaka.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

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
        Problem tmp = problemService.getProblemById(problem.getId());
        if (Objects.isNull(tmp)) {
            return new ResponseResult(400, "获取失败!");
        } else {
            return new ResponseResult(200, "获取成功!", tmp);
        }
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

    @RequestMapping(value="/addProblem", method = RequestMethod.POST)
    ResponseResult addProblem(@RequestBody Problem problem) {
        problemService.addProblem(problem);
        return new ResponseResult(200, "加入成功!");
    }

    @RequestMapping(value="/deleteProblem", method = RequestMethod.POST)
    ResponseResult deleteProblem(@RequestBody Problem problem) {
        problemService.deleteProblem(problem.getId());
        return new ResponseResult(200, "删除成功!");
    }
}
