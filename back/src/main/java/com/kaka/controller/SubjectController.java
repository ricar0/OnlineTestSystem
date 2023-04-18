package com.kaka.controller;

import com.alibaba.fastjson.JSONObject;
import com.kaka.entity.Problem;
import com.kaka.entity.ProblemFilter;
import com.kaka.entity.Subject;
import com.kaka.service.ProblemService;
import com.kaka.service.SubjectService;
import com.kaka.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/subject")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private ProblemService problemService;

    @RequestMapping(value = "/getSubjectAll", method = RequestMethod.GET)
    ResponseResult getSubjectAll() {
        return new ResponseResult(200, "获取成功!", subjectService.getSubjectAll());
    }

    @RequestMapping(value="/getSubjectProblemNumber", method = RequestMethod.GET)
    ResponseResult getSubjectProblemNumber() {
        List<String> subjectList = subjectService.getSubjectAll();
        List<JSONObject> jsonObjectList = new ArrayList<>();
        for (int i = 0; i < subjectList.size(); i++) {
            ProblemFilter problemFilter = new ProblemFilter();
            List<String> source = new ArrayList<>();
            source.add(subjectList.get(i));
            problemFilter.setSourceList(source);
            List<Problem> problemList = problemService.getProblemByFilter(problemFilter);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("subject", subjectList.get(i));
            jsonObject.put("count", problemList.size());
            jsonObjectList.add(jsonObject);
        }
        return new ResponseResult(200, "获取成功!", jsonObjectList);
    }

    @RequestMapping(value="/addSubject", method = RequestMethod.POST)
    ResponseResult addSubject(@RequestBody Subject subject) {
        Subject tmp = subjectService.findSubjectBySource(subject.getSource());
        if (Objects.isNull(tmp)) {
            subjectService.addSubject(subject);
            return new ResponseResult(200, "添加成功!");
        } else {
            return new ResponseResult(400, "已存在该学科!");
        }
    }

    @RequestMapping(value="/deleteSubject", method = RequestMethod.POST)
    ResponseResult deleteSubject(@RequestBody Subject subject) {
        subjectService.deleteSubject(subject.getSource());
        return new ResponseResult(200, "已删除!");
    }
}
