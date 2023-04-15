package com.kaka.service.Impl;

import com.kaka.entity.*;
import com.kaka.mapper.ExamMapper;
import com.kaka.service.ExamService;
import com.kaka.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExamServiceImpl implements ExamService {

    @Autowired
    private ExamMapper examMapper;

    @Autowired
    private RedisCache redisCache;

    @Override
    public List<Exam> getAll() {
        return examMapper.getAll();
    }

    @Override
    public Long addExam(Exam exam) {
        examMapper.addExam(exam);
        return exam.getId();
    }

    @Override
    public List<Exam> getMyExam(MyExamFilter myExamFilter) {
        return examMapper.getMyExam(myExamFilter);
    }

    @Override
    public Exam getExamById(Long id) {
        return examMapper.getExamById(id);
    }

    @Override
    public List<Problem> getProblemById(Long id) {
        return examMapper.getProblemById(id);
    }

    @Override
    public void startExam(MyExam myExam) {
        examMapper.startExam(myExam);
    }

    @Override
    public void endExam(MyExam myExam) {
        examMapper.endExam(myExam);
    }

    @Override
    public List<Exam> getExamByFilter(ExamFilter examFilter) {
        return examMapper.getExamByFilter(examFilter);
    }

    @Override
    public Integer getUserNumberByExamId(Long id) {
        return examMapper.getUserNumberByExamId(id);
    }

    @Override
    public void deleteExam(Long id) {
        examMapper.deleteExam(id);
    }

    @Override
    public void deleteExamProblem(Long id) {
        examMapper.deleteExamProblem(id);
    }

    @Override
    public void updateExamInfo(Exam exam) {
        examMapper.updateExamInfo(exam);
    }

    @Override
    public void registerExam(MyExam myExam) {
        examMapper.registerExam(myExam);
        examMapper.addExamNumber(myExam.getExam_id());
    }

    @Override
    public void initExam(Exam exam, Long id) {
        ExamCookie examCookie = new ExamCookie();
        List<List<Integer>> list = new ArrayList<List<Integer>>();
        for (int i = 0; i < exam.getMultipleNum(); i++) {
            List<Integer> l = new ArrayList<Integer>();
            list.add(l);
        }
        examCookie.setMultipleAnswer(list);
        List<Integer> list1 = new ArrayList<Integer>();
        examCookie.setSingleAnswer(list1);
        examCookie.setJudgeAnswer(list1);

        List<Boolean> singleList = new ArrayList<Boolean>();
        List<Boolean> multipleList = new ArrayList<Boolean>();
        List<Boolean> judgeList = new ArrayList<Boolean>();
        for (int i = 0; i < exam.getSingleNum(); i++) singleList.add(false);
        for (int i = 0; i < exam.getMultipleNum(); i++) multipleList.add(false);
        for (int i = 0; i < exam.getTfNum(); i++) judgeList.add(false);
        examCookie.setSingleisClick(singleList);
        examCookie.setMultipleisClick(multipleList);
        examCookie.setJudgeisClick(judgeList);
        examCookie.setSingleisMark(singleList);
        examCookie.setMultipleisMark(multipleList);
        examCookie.setJudgeisMark(judgeList);
        examCookie.setBg_flag(false);
        examCookie.setUser_id(id);
        examCookie.setSingleNum(exam.getSingleNum());
        examCookie.setMultipleNum(exam.getMultipleNum());
        examCookie.setJudgeNum(exam.getTfNum());
        examCookie.setExam_id(exam.getId());
        redisCache.setCacheObject("examcookies:"+id+'-'+exam.getId(), examCookie);
    }

    @Override
    public MyExam getRegisterState(MyExam myExam) {
        return examMapper.getRegisterState(myExam);
    }

}
