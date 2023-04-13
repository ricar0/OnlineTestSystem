package com.kaka.controller;

import com.kaka.service.SubjectService;
import com.kaka.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/subject")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @RequestMapping(value = "/getSubjectAll", method = RequestMethod.GET)
    ResponseResult getSubjectAll() {
        return new ResponseResult(200, "获取成功!", subjectService.getSubjectAll());
    }
}
