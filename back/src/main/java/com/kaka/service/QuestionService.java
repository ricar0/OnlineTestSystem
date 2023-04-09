package com.kaka.service;

import com.kaka.entity.Problem;
import com.kaka.entity.ProblemFilter;
import com.kaka.mapper.ProblemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class QuestionService {

    private static ProblemMapper problemMapper;

    @Autowired
    private ApplicationContext applicationContext;

    @PostConstruct
    public void init() {
        problemMapper = applicationContext.getBean(ProblemMapper.class);
    }

    public static List<Problem> getQuestionArray(int type, List<String> coverage, String source) {
        ProblemFilter problemFilter = new ProblemFilter();
        problemFilter.setCategoryList(coverage);
        List<String> sourceList = new ArrayList<>();
        sourceList.add(source);
        problemFilter.setSourceList(sourceList);
        List<String> labelList = new ArrayList<>();
        if (type == 1) {
            labelList.add("single");
        } else if (type == 2) {
            labelList.add("multiple");
        } else if (type == 3) {
            labelList.add("tf");
        }
        problemFilter.setLabelList(labelList);
        return problemMapper.getProblemByFilter(problemFilter);
    }


}
