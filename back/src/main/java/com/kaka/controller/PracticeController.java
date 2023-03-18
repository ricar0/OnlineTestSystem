package com.kaka.controller;

import com.kaka.entity.Practice;
import com.kaka.service.PracticeService;
import com.kaka.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/practice")
public class PracticeController {

    @Autowired
    private PracticeService practiceService;

    @RequestMapping(value = "/addPractice", method = RequestMethod.POST)
    ResponseResult addPractice(@RequestBody Practice practice) {
        practiceService.addPractice(practice);
        return new ResponseResult(200, "添加成功!");
    }

    @RequestMapping(value = "/getMyPractice", method = RequestMethod.GET)
    ResponseResult getMyPractice(HttpServletRequest request) {
        return new ResponseResult(200, "获取成功!", practiceService.getMyPractice(request));
    }
}
