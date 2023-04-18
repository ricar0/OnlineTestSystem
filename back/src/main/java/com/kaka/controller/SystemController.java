package com.kaka.controller;

import com.alibaba.fastjson.JSONObject;
import com.kaka.entity.Exam;
import com.kaka.entity.Problem;
import com.kaka.entity.ProblemFilter;
import com.kaka.service.ExamService;
import com.kaka.service.ProblemService;
import com.kaka.service.SubjectService;
import com.kaka.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/system")
public class SystemController {
    @Autowired
    private SubjectService subjectService;

    @Autowired
    private ProblemService problemService;

    @Autowired
    private ExamService examService;

    @RequestMapping(value = "/getSystemInfo", method = RequestMethod.GET)
    ResponseResult getSystemInfo() {
        List<String> subjectList = subjectService.getSubjectAll();
        ProblemFilter problemFilter = new ProblemFilter();
        List<Problem> problemList = problemService.getProblemByFilter(problemFilter);
        List<Exam> examList = examService.getAll();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("subject", subjectList.size());
        jsonObject.put("problem", problemList.size());
        jsonObject.put("exam", examList.size());
        return new ResponseResult(200, "获取成功!", jsonObject);
    }
}
