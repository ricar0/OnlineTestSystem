package com.kaka.mapper;

import com.kaka.entity.*;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PracticeMapper {
    public void addPractice(Practice practice);

    public List<Practice> getPracticeAll();

    public List<Practice> getPracticeByFilter(PracticeFilter practiceFilter);

    public List<Practice> getMyPractice(MyPracticeFilter myPracticeFilter);

    public void addProblemToPractice(ProblemToPaper problem);

    public Practice getPracticeInfo(Long id);

    public void registerPractice(MyPractice myPractice);

    public MyPractice getRegisterState(MyPractice myPractice);

    public void addPracticeNumber(Long id);
}
