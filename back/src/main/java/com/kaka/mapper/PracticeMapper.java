package com.kaka.mapper;

import com.kaka.entity.Practice;
import com.kaka.entity.PracticeFilter;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Mapper
@Repository
public interface PracticeMapper {
    public void addPractice(Practice practice);

    public List<Practice> getMyPractice(Long user_id);

    public List<Practice> getPracticeAll();

    public List<Practice> getPracticeByFilter(PracticeFilter practiceFilter);
}
