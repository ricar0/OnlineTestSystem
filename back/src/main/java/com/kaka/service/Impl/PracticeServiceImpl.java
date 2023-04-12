package com.kaka.service.Impl;

import com.kaka.entity.MyPracticeFilter;
import com.kaka.entity.Practice;
import com.kaka.entity.PracticeFilter;
import com.kaka.mapper.PracticeMapper;
import com.kaka.service.PracticeService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.kaka.utils.JwtUtil.parseJWT;

@Service
public class PracticeServiceImpl implements PracticeService {

    @Autowired
    private PracticeMapper practiceMapper;

    @Override
    public void addPractice(Practice practice) {
        practiceMapper.addPractice(practice);
    }

    @Override
    public List<Practice> getMyPractice(MyPracticeFilter myPracticeFilter) {
        return practiceMapper.getMyPractice(myPracticeFilter);
    }

    @Override
    public List<Practice> getPracticeAll() {
        return practiceMapper.getPracticeAll();
    }

    @Override
    public List<Practice> getPracticeByFilter(PracticeFilter practiceFilter) {
        return practiceMapper.getPracticeByFilter(practiceFilter);
    }
}
