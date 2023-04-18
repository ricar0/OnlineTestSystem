package com.kaka.service;

import com.kaka.entity.*;
import com.kaka.utils.ResponseResult;

import java.util.List;

public interface PracticeService {
    public Long addPractice(Practice practice);

    public List<Practice> getMyPractice(MyPracticeFilter myPracticeFilter);

    public List<Practice> getPracticeAll();

    public List<Practice> getPracticeByFilter(PracticeFilter practiceFilter);

    ResponseResult generateProblems(Practice practice, Long id);

    public Practice getPracticeInfo(Long id);

    public void registerPractice(MyPractice myPractice);

    public MyPractice getRegisterState(MyPractice myPractice);

    public List<Problem> getProblemByPracticeId(Long id);

    void addPracticeResult(ScoreResult scoreResult);

    void deletePractice(Long id);

    List<ScoreResult> getRecentPracticeResult(Long id);
}
