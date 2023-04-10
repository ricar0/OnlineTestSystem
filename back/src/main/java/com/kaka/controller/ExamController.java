package com.kaka.controller;

import com.kaka.entity.*;
import com.kaka.mapper.ExamMapper;
import com.kaka.service.ExamService;
import com.kaka.utils.RedisCache;
import com.kaka.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/exam")
public class ExamController {
    @Autowired
    private ExamService examService;

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private ExamMapper examMapper;

    @RequestMapping(value="/getAll", method = RequestMethod.GET)
    public ResponseResult getAll() {
        return new ResponseResult(200, "查询成功!", examService.getAll());
    }

    @RequestMapping(value="/addExam", method = RequestMethod.POST)
    public ResponseResult addExam(@RequestBody Exam exam) {
        examService.addExam(exam);
        return new ResponseResult(200, "添加成功!");
    }

    @RequestMapping(value = "/getMyExam", method = RequestMethod.POST)
    public ResponseResult getMyExam(@RequestBody MyExamFilter myExamFilter) {
        return new ResponseResult(200, "获取成功!", examService.getMyExam(myExamFilter));
    }

    @RequestMapping(value = "/getMyExamNumber", method = RequestMethod.POST)
    public ResponseResult getMyExamNumber(@RequestBody MyExamFilter myExamFilter) {
        return new ResponseResult(200, "获取成功!", examService.getMyExam(myExamFilter).size());
    }

    @RequestMapping(value="/getUserNumberByExamId", method=RequestMethod.POST)
    public ResponseResult getUserByExamId(@RequestBody Exam exam) {
        return new ResponseResult(200, "获取成功!", examService.getUserNumberByExamId(exam.getId()));
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
        PaperBean paperBean = new PaperBean();
        List<Problem> list = examService.getProblemById(id);
        Exam e = examService.getExamById(id);
        paperBean.setProblems(list);
        int singleNum = 0, multipleNum = 0, tfNum = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getLabel().equals("single")) singleNum++;
            if (list.get(i).getLabel().equals("multiple")) multipleNum++;
            if (list.get(i).getLabel().equals("tf")) tfNum++;
        }
        paperBean.setSingleNum(singleNum);
        paperBean.setMultipleNum(multipleNum);
        paperBean.setTfNum(tfNum);
        paperBean.setSingleScore(e.getSingleScore());
        paperBean.setMultipleScore(e.getMultipleScore());
        paperBean.setTfScore(e.getTfScore());
        return new ResponseResult(200, "获取成功!", paperBean);
    }

    @RequestMapping(value = "/initExamCookies", method = RequestMethod.POST)
    public ResponseResult initExamCookies(@RequestBody ExamCookie examCookie) {
        examCookie.setMinutes(examService.getExamById(examCookie.getExam_id()).getTotalTime());
        examCookie.setSeconds(0);
        List<List<Integer>> list = new ArrayList<List<Integer>>();
        for (int i = 0; i < examCookie.getMultipleNum(); i++) {
            List<Integer> l = new ArrayList<Integer>();
            list.add(l);
        }
        examCookie.setMultipleAnswer(list);
        List<Integer> list1 = new ArrayList<Integer>();
        examCookie.setSingleAnswer(list1);
        examCookie.setJudgeAnswer(list1);

        List<Boolean> list2 = new ArrayList<Boolean>();
        for (int i = 0; i < examCookie.getMultipleNum(); i++) list2.add(false);
        examCookie.setSingleisClick(list2);
        examCookie.setMultipleisClick(list2);
        examCookie.setJudgeisClick(list2);
        examCookie.setSingleisMark(list2);
        examCookie.setMultipleisMark(list2);
        examCookie.setJudgeisMark(list2);
        examCookie.setBg_flag(false);
        redisCache.setCacheObject("examcookies:"+examCookie.getUser_id()+examCookie.getExam_id(), examCookie);
        return new ResponseResult(200, "设置成功!");
    }

    @RequestMapping(value = "/setExamCookies", method = RequestMethod.POST)
    public ResponseResult setExamCookies(@RequestBody ExamCookie examCookie) {
        int totalTime = examService.getExamById(examCookie.getExam_id()).getTotalTime();
        MyExam myExam = new MyExam();
        myExam.setExam_id(examCookie.getExam_id());
        myExam.setUser_id(examCookie.getUser_id());
        Date date = examMapper.getExamInfo(myExam).getStart_time();
        Calendar startTime = Calendar.getInstance();
        startTime.setTime(date);
        Calendar currentTime = Calendar.getInstance();
        currentTime.setTime(new Date());
        Long startSecond = startTime.getTimeInMillis();
        Long currentSecond = currentTime.getTimeInMillis();
        int totleSecond = totalTime * 60;
        Long remainingTime = (totleSecond-(currentSecond-startSecond)/1000);
        int minutes = (int)(remainingTime / 60);
        int seconds = (int)(remainingTime - remainingTime/60*60);
        examCookie.setMinutes(minutes);
        examCookie.setSeconds(seconds);
        redisCache.setCacheObject("examcookies:"+examCookie.getUser_id()+examCookie.getExam_id(), examCookie);
        return new ResponseResult(200, "设置成功!");
    }
    @RequestMapping(value = "/getExamCookies", method = RequestMethod.POST)
    public ResponseResult getExamCookies(@RequestBody ExamCookie examCookie) {
        ExamCookie res = redisCache.getCacheObject("examcookies:"+examCookie.getUser_id()+examCookie.getExam_id());
        return new ResponseResult(200, "获取成功!", res);
    }

    @RequestMapping(value="/startExam", method = RequestMethod.POST)
    public ResponseResult startExam(@RequestBody MyExam myExam) {
        ExamCookie examCookie = redisCache.getCacheObject("examcookies:"+myExam.getUser_id()+myExam.getExam_id());
        System.out.println(examCookie);
        MyExam tmp = examMapper.getExamInfo(myExam);
        if (!Objects.isNull(tmp.getEnd_time())) {
            return new ResponseResult(400, "考试已结束!");
        } else {
            if (Objects.isNull(tmp.getStart_time())) {
                myExam.setStart_time(new Date());
                examService.startExam(myExam);
            }
            return new ResponseResult(200, "已开始考试!");
        }
    }

    @RequestMapping(value="/endExam", method = RequestMethod.POST)
    public ResponseResult endExam(@RequestBody MyExam myExam) {
        Date end_time = new Date();
        myExam.setEnd_time(end_time);
        List<Problem> list = examService.getProblemById(myExam.getExam_id());
        Exam exam = examService.getExamById(myExam.getExam_id());
        ExamCookie examCookie = redisCache.getCacheObject("examcookies:"+myExam.getUser_id()+myExam.getExam_id());
        List<Integer> singleAnswer = examCookie.getSingleAnswer();
        List<List<Integer>> multipleAnswer = examCookie.getMultipleAnswer();
        List<Integer> judgeAnswer = examCookie.getJudgeAnswer();
        int score = 0;
        for (int i = 0; i < singleAnswer.size(); i++) {
            if (Objects.isNull(singleAnswer.get(i))) continue;
            String ans = "";
            if (singleAnswer.get(i) == 1) ans = "A";
            else if (singleAnswer.get(i) == 2) ans = "B";
            else if (singleAnswer.get(i) == 3) ans = "C";
            else ans = "D";
            if (ans.equals(list.get(i).getAccept())) score += exam.getSingleScore();
        }
        for (int i = 0; i < multipleAnswer.size(); i++) {
            if (Objects.isNull(multipleAnswer.get(i))) continue;
            String ans = "";
            for (int j = 0; j < multipleAnswer.get(i).size(); j++) {
                if (multipleAnswer.get(i).get(j) == 1) ans += "A";
                else if (multipleAnswer.get(i).get(j) == 2) ans += "B";
                else if (multipleAnswer.get(i).get(j) == 3) ans += "C";
                else ans += "D";
            }
            if (ans.equals(list.get(i+ examCookie.getSingleNum()).getAccept())) score += exam.getMultipleScore();
        }
        for (int i = 0; i < judgeAnswer.size(); i++) {
            if (Objects.isNull(judgeAnswer.get(i))) continue;
            String ans = "";
            if (judgeAnswer.get(i) == 1) ans = "A";
            else ans = "B";
            if (ans.equals(list.get(i+examCookie.getSingleNum()+ examCookie.getMultipleNum()).getAccept())) score += exam.getTfScore();
        }
        myExam.setScore(score);
        examService.endExam(myExam);
        return new ResponseResult(200, "成功!");
    }

    @RequestMapping(value="/getExamResult", method = RequestMethod.POST)
    public ResponseResult getExamResult(@RequestBody MyExam myExam) {
        return new ResponseResult(200, "获取成功!", examMapper.getExamInfo(myExam));
    }

    @RequestMapping(value="/getExamByFilter", method = RequestMethod.POST)
    public ResponseResult getExamByFilter(@RequestBody ExamFilter examFilter) {
        return new ResponseResult(200, "获取成功!", examService.getExamByFilter(examFilter));
    }

    @RequestMapping(value="/getAllNumber3", method = RequestMethod.POST)
    public ResponseResult getAllNumber3(@RequestBody ExamFilter examFilter) {
        return new ResponseResult(200, "获取成功!", examService.getExamByFilter(examFilter).size());
    }

    @RequestMapping(value="/deleteExam", method = RequestMethod.POST)
    public ResponseResult deleteExam(@RequestBody Exam exam) {
        examService.deleteExam(exam.getId());
        examService.deleteExamProblem(exam.getId());
        return new ResponseResult(200, "删除成功!");
    }

    @RequestMapping(value="/updateExamInfo", method = RequestMethod.POST)
    public ResponseResult updateExamInfo(@RequestBody Exam exam) {
        examService.updateExamInfo(exam.getId());
        examService.updateExamInfo(exam.getId());
        return new ResponseResult(200, "删除成功!");
    }
}
