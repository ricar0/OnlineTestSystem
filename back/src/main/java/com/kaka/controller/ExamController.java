package com.kaka.controller;

import com.alibaba.fastjson.JSONObject;
import com.kaka.entity.*;
import com.kaka.mapper.ExamMapper;
import com.kaka.service.ExamService;
import com.kaka.service.PaperService;
import com.kaka.service.UserService;
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

    @Autowired
    private PaperService paperService;

    @Autowired
    private UserService userService;

    @RequestMapping(value="/getAll", method = RequestMethod.GET)
    public ResponseResult getAll() {
        return new ResponseResult(200, "查询成功!", examService.getAll());
    }

    @RequestMapping(value="/addExam", method = RequestMethod.POST)
    public ResponseResult addExam(@RequestBody PaperBean paperBean) {
        Long id = examService.addExam(paperBean.getExam());
        paperBean.getExam().setId(id);
        paperService.addPaper(paperBean);
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

    @RequestMapping(value = "/setExamCookies", method = RequestMethod.POST)
    public ResponseResult setExamCookies(@RequestBody ExamCookie examCookie) {
        MyExam myExam = new MyExam();
        myExam.setExam_id(examCookie.getExam_id());
        myExam.setUser_id(examCookie.getUser_id());
        redisCache.setCacheObject("examcookies:"+examCookie.getUser_id()+'-'+examCookie.getExam_id(), examCookie);
        return new ResponseResult(200, "设置成功!");
    }
    @RequestMapping(value = "/getExamCookies", method = RequestMethod.POST)
    public ResponseResult getExamCookies(@RequestBody ExamCookie examCookie) {
        ExamCookie res = redisCache.getCacheObject("examcookies:"+examCookie.getUser_id()+'-'+examCookie.getExam_id());
        return new ResponseResult(200, "获取成功!", res);
    }

    @RequestMapping(value="/startExam", method = RequestMethod.POST)
    public ResponseResult startExam(@RequestBody MyExam myExam) {
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
        ExamCookie examCookie = redisCache.getCacheObject("examcookies:"+myExam.getUser_id()+'-'+myExam.getExam_id());
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
        examService.updateExamInfo(exam);
        return new ResponseResult(200, "删除成功!");
    }

    @RequestMapping(value="/registerExam", method = RequestMethod.POST)
    public ResponseResult registerExam(@RequestBody MyExam myExam) {
        examService.registerExam(myExam);
        Exam exam = examService.getExamById(myExam.getExam_id());
        examService.initExam(exam, myExam.getUser_id());
        return new ResponseResult(200, "报名成功!");
    }

    @RequestMapping(value="/getRegisterState", method = RequestMethod.POST)
    public ResponseResult getRegisterState(@RequestBody MyExam myExam) {
        MyExam tmp = examService.getRegisterState(myExam);
        if (Objects.isNull(tmp)) {
            return new ResponseResult(200, "获取成功!", 0);
        } else {
            return new ResponseResult(200, "获取成功!", 1);
        }
    }

    @RequestMapping(value="/getRecentExamResult", method = RequestMethod.POST)
    public ResponseResult getRecentExamResult(@RequestBody User user) {
        List<ScoreResult> scoreResultList = examService.getRecentExamResult(user.getId());
        for (ScoreResult scoreResult: scoreResultList) {
            Exam exam = examService.getExamById(scoreResult.getExam_id());
            scoreResult.setSource(exam.getSource());
        }
        return new ResponseResult(200, "获取成功!", scoreResultList);
    }

    @RequestMapping(value="/getExamScoreResult", method = RequestMethod.POST)
    public ResponseResult getExamScoreResult(@RequestBody Exam exam) {
        return new ResponseResult(200, "获取成功!", examService.getExamScoreResult(exam.getId()));
    }

    @RequestMapping(value="/getRecentExamScore", method = RequestMethod.GET)
    public ResponseResult getRecentExamScore() {
        List<ScoreResult> scoreResultList = examService.getRecentExamScore();
        for (ScoreResult scoreResult: scoreResultList) {
            Exam exam = examService.getExamById(scoreResult.getExam_id());
            scoreResult.setSource(exam.getSource());
        }
        return new ResponseResult(200, "获取成功!", scoreResultList);
    }

    @RequestMapping(value="/getExamRank", method = RequestMethod.POST)
    public ResponseResult getExamRank(@RequestBody Exam exam) {
        List<MyExam> myExamList = examService.getExamRank(exam.getId());
        List<JSONObject> jsonObjectList = new ArrayList<>();
        int count = 0;
        for (MyExam myExam : myExamList) {
            count++;
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("score", myExam.getScore());
            jsonObject.put("end_time", myExam.getEnd_time().toString());
            User user = userService.getUserInfoById(myExam.getUser_id());
            jsonObject.put("rank", count);
            jsonObject.put("name", user.getUsername());
            jsonObjectList.add(jsonObject);
        }
        return new ResponseResult(200, "获取成功!", jsonObjectList);
    }
}
