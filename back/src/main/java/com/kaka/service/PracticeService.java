package com.kaka.service;

import com.kaka.entity.Practice;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface PracticeService {
    public void addPractice(Practice practice);

    public List<Practice> getMyPractice(HttpServletRequest request);
}
