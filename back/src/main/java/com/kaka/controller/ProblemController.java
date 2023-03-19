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
        return new ResponseResult(200, "获取成功!", problemService.getProblemByFilter(problemFilter));
    }
}
