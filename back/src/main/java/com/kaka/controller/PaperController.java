package com.kaka.controller;

import com.kaka.entity.Exam;
import com.kaka.entity.PaperBean;
import com.kaka.entity.RuleBean;
import com.kaka.service.ExamService;
import com.kaka.service.PaperService;
import com.kaka.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/paper")
public class PaperController {

    @Autowired
    private ExamService examService;

    @Autowired
    private PaperService paperService;

    @RequestMapping(value="/addExamByRand", method = RequestMethod.POST)
    public ResponseResult addExamByRand(@RequestBody Exam exam) {
        Long id = examService.addExam(exam);
        exam.setId(id);
        return paperService.addPaperByRand(exam);
    }
    @RequestMapping(value="addExamByGeneticAlgorithm", method = RequestMethod.POST)
    public ResponseResult addExamByGeneticAlgorithm(@RequestBody RuleBean ruleBean) {
        Exam exam = new Exam();
        exam.setSource(ruleBean.getSource());
        exam.setDescription(ruleBean.getDescription());
        exam.setStart_time(ruleBean.getStart_time());
        exam.setEnd_time(ruleBean.getEnd_time());
        exam.setMultipleNum(ruleBean.getMultipleNum());
        exam.setMultipleScore(ruleBean.getMultipleScore());
        exam.setPassword(ruleBean.getPassword());
        exam.setPermission(ruleBean.getPermission());
        exam.setSingleNum(ruleBean.getSingleNum());
        exam.setSingleScore(ruleBean.getSingleScore());
        exam.setTeacher(ruleBean.getTeacher());
        exam.setTfNum(ruleBean.getTfNum());
        exam.setTfScore(ruleBean.getTfScore());
        exam.setTotalScore(ruleBean.getTotalScore());
        exam.setTotalTime(ruleBean.getTotalTime());
        Long id = examService.addExam(exam);
        ruleBean.setExam_id(id);
        return paperService.addExamByGeneticAlgorithm(ruleBean);
    }
}
