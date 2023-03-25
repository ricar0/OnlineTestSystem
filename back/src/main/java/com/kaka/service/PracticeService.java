package com.kaka.service;

import com.kaka.entity.Practice;
import com.kaka.entity.PracticeFilter;
import com.kaka.utils.ResponseResult;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface PracticeService {
    public void addPractice(Practice practice);

    public List<Practice> getMyPractice(HttpServletRequest request);

    public List<Practice> getPracticeAll();

    public List<Practice> getPracticeByFilter(PracticeFilter practiceFilter);
}
