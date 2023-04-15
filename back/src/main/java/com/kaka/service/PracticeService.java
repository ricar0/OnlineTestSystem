package com.kaka.service;

import com.kaka.entity.MyPractice;
import com.kaka.entity.MyPracticeFilter;
import com.kaka.entity.Practice;
import com.kaka.entity.PracticeFilter;
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
}
