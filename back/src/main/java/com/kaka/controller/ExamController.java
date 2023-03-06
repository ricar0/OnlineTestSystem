package com.kaka.controller;

import com.kaka.entity.Exam;
import com.kaka.service.ExamService;
import com.kaka.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/exam")
public class ExamController {
    @Autowired
    private ExamService examService;

    @RequestMapping(value="/getAll", method = RequestMethod.GET)
    public ResponseResult getAll() {
        return new ResponseResult(200, "查询成功!", examService.getAll());
    }

    @RequestMapping(value="/addExam", method = RequestMethod.POST)
    public ResponseResult addExam(@RequestBody Exam exam) {
        examService.addExam(exam);
        return new ResponseResult(200, "添加成功!");
    }

    @RequestMapping(value = "/getMyExam", method = RequestMethod.GET)
    public ResponseResult getMyExam(HttpServletRequest request) {
        return new ResponseResult(200, "获取成功!", examService.getMyExam(request));
    }
}
