package com.kaka.service;

import com.kaka.entity.PaperBean;
import com.kaka.entity.ProblemToPaper;
import com.kaka.mapper.PaperMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaperServiceImpl implements PaperService{

    @Autowired
    private PaperMapper paperMapper;
    @Override
    public void addPaper(PaperBean paperBean) {
        for (int i = 0; i <paperBean.getProblems().size(); i++) {
            ProblemToPaper problemToPaper = new ProblemToPaper();
            problemToPaper.setProblem_id(paperBean.getProblems().get(i).getId());
            problemToPaper.setExam_id(paperBean.getExam().getId());
            paperMapper.addPaper(problemToPaper);
        }
    }
}
