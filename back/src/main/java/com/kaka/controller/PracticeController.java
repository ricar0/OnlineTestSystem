package com.kaka.controller;

import com.kaka.entity.*;
import com.kaka.service.PracticeService;
import com.kaka.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/practice")
public class PracticeController {

    @Autowired
    private PracticeService practiceService;

    @RequestMapping(value = "/addPractice", method = RequestMethod.POST)
    ResponseResult addPractice(@RequestBody Practice practice) {
        Long id = practiceService.addPractice(practice);
        MyPractice myPractice = new MyPractice();
        myPractice.setUser_id(practice.getUser_id());
        myPractice.setPractice_id(id);
        practiceService.registerPractice(myPractice);
        practice.setId(id);
        ResponseResult ans = practiceService.generateProblems(practice,id);
        if (ans.getCode() == 400) {
            practiceService.deletePractice(myPractice.getPractice_id());
        }
        return ans;
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

    @RequestMapping(value="/getPracticeInfo", method = RequestMethod.POST)
    ResponseResult getPracticeInfo(@RequestBody Practice practice) {
        return new ResponseResult(200, "获取成功!", practiceService.getPracticeInfo(practice.getId()));
    }

    @RequestMapping(value="/registerPractice", method = RequestMethod.POST)
    ResponseResult registerPractice(@RequestBody MyPractice myPractice) {
        practiceService.registerPractice(myPractice);
        return new ResponseResult(200, "添加成功!");
    }

    @RequestMapping(value="/getRegisterState", method = RequestMethod.POST)
    ResponseResult getRegisterState(@RequestBody MyPractice myPractice) {
        MyPractice tmp = practiceService.getRegisterState(myPractice);
        if (Objects.isNull(tmp))
            return new ResponseResult(200, "未报名!", 0);
        else
            return new ResponseResult(200, "已报名!", 1);
    }

    @RequestMapping(value="/getPaperInfoByPracticeId", method = RequestMethod.POST)
    ResponseResult getPaperInfoByPracticeId(@RequestBody Practice practice) {
        List<Problem> problems = practiceService.getProblemByPracticeId(practice.getId());
        Practice practiceInfo = practiceService.getPracticeInfo(practice.getId());
        PaperBean paperBean = new PaperBean();
        paperBean.setPractice(practiceInfo);
        paperBean.setProblems(problems);
        return new ResponseResult(200, "获取成功!", paperBean);
    }

    @RequestMapping(value="/addPracticeResult", method = RequestMethod.POST)
    ResponseResult addPracticeResult(@RequestBody ScoreResult scoreResult) {
        practiceService.addPracticeResult(scoreResult);
        return new ResponseResult(200, "加入成功!");
    }

    @RequestMapping(value="/deletePractice", method = RequestMethod.POST)
    ResponseResult deletePractice(@RequestBody Practice practice) {
        practiceService.deletePractice(practice.getId());
        return new ResponseResult(200, "删除成功!");
    }
}
