package com.kaka.controller;

import com.kaka.entity.Exam;
import com.kaka.entity.Paper;
import com.kaka.entity.Problem;
import com.kaka.service.ExamService;
import com.kaka.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;
import java.util.List;

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

    @RequestMapping(value = "/getExamById/{id}")
    public ResponseResult getExamById(@PathVariable("id") Long id) {
        return new ResponseResult(200, "获取成功!", examService.getExamById(id));
    }

    @RequestMapping(value = "/getProblemById", method = RequestMethod.POST)
    public ResponseResult getProblemById(@RequestBody Exam exam) {
        return new ResponseResult(200, "获取成功!", examService.getProblemById(exam.getId()));
    }

    @RequestMapping(value = "/getPaperInfoById", method = RequestMethod.POST)
    public ResponseResult getPaperInfoById(@RequestBody Exam exam) {
        Long id = exam.getId();
        Paper paper = new Paper();
        List<Problem> list = examService.getProblemById(id);
        Exam e = examService.getExamById(id);
        paper.setProblems(list);
        int singleNum = 0, multipleNum = 0, tfNum = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getLabel().equals("single")) singleNum++;
            if (list.get(i).getLabel().equals("multiple")) multipleNum++;
            if (list.get(i).getLabel().equals("tf")) tfNum++;
        }
        paper.setSingleNum(singleNum);
        paper.setMultipleNum(multipleNum);
        paper.setTfNum(tfNum);
        paper.setSingleScore(e.getSingleScore());
        paper.setMultipleScore(e.getMultipleScore());
        paper.setTfScore(e.getTfScore());
        return new ResponseResult(200, "获取成功!", paper);
    }
}
