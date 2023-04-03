package com.kaka.controller;

import com.kaka.entity.MyPracticeFilter;
import com.kaka.entity.Practice;
import com.kaka.entity.PracticeFilter;
import com.kaka.service.PracticeService;
import com.kaka.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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

    @RequestMapping(value = "/getMyPractice", method = RequestMethod.POST)
    ResponseResult getMyPractice(@RequestBody MyPracticeFilter myPracticeFilter) {
        return new ResponseResult(200, "获取成功!", practiceService.getMyPractice(myPracticeFilter));
    }

    @RequestMapping(value = "/getMyPracticeNumber", method = RequestMethod.POST)
    ResponseResult getMyPracticeNumber(@RequestBody MyPracticeFilter myPracticeFilter) {
        return new ResponseResult(200, "获取成功!", practiceService.getMyPractice(myPracticeFilter).size());
    }

    @RequestMapping(value = "/getPracticeAll", method = RequestMethod.GET)
    ResponseResult getPracticeAll() {
        return new ResponseResult(200, "获取成功!", practiceService.getPracticeAll());
    }

    @RequestMapping(value="/getPracticeByFilter", method = RequestMethod.POST)
    ResponseResult getPracticeByFilter(@RequestBody PracticeFilter practiceFilter) {
        return new ResponseResult(200, "获取成功!", practiceService.getPracticeByFilter(practiceFilter));
    }

    @RequestMapping(value="/getAllNumber", method = RequestMethod.POST)
    ResponseResult getAllNumber(@RequestBody PracticeFilter practiceFilter) {
        List<Practice> list = practiceService.getPracticeByFilter(practiceFilter);
        return new ResponseResult(200, "获取成功!", list.size());
    }
}
