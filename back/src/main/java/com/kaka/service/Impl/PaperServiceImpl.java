package com.kaka.service.Impl;

import com.kaka.entity.*;
import com.kaka.mapper.PaperMapper;
import com.kaka.mapper.ProblemMapper;
import com.kaka.service.PaperService;
import com.kaka.utils.GeneticAlgorithm;
import com.kaka.utils.Population;
import com.kaka.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PaperServiceImpl implements PaperService {

    @Autowired
    private PaperMapper paperMapper;

    @Autowired
    private ProblemMapper problemMapper;

    @Override
    public void addPaper(PaperBean paperBean) {
        for (int i = 0; i <paperBean.getProblems().size(); i++) {
            ProblemToPaper problemToPaper = new ProblemToPaper();
            problemToPaper.setProblem_id(paperBean.getProblems().get(i).getId());
            problemToPaper.setExam_id(paperBean.getExam().getId());
            paperMapper.addPaper(problemToPaper);
        }
    }

    @Override
    public ResponseResult addPaperByRand(Exam exam) {
        ProblemFilter problemFilter = new ProblemFilter();
        List<String> sourceList = new ArrayList<>();
        sourceList.add(exam.getSource());
        problemFilter.setSourceList(sourceList);
        List<Problem> singleList = problemMapper.getProblemByFilter(problemFilter);
        List<Problem> multipleList = problemMapper.getProblemByFilter(problemFilter);
        List<Problem> tfList = problemMapper.getProblemByFilter(problemFilter);
        if (singleList.size() < exam.getSingleNum()) {
            return new ResponseResult(400, "单选题数量不够!");
        } else if (multipleList.size() < exam.getMultipleNum()) {
            return new ResponseResult(400, "多选题数量不够!");
        } else if (tfList.size() < exam.getTfNum()) {
            return new ResponseResult(400, "判断题数量不够!");
        }
        Random random = new Random();
        Set<Long> chosenIndex = new HashSet<>();
        int count = 0;
        while (count < exam.getSingleNum()) {
            int index = random.nextInt(singleList.size());
            if (!chosenIndex.contains(index)) {
                chosenIndex.add((long)index);
                count++;
                ProblemToPaper problemToPaper = new ProblemToPaper();
                problemToPaper.setProblem_id(singleList.get(index).getId());
                problemToPaper.setExam_id(exam.getId());
                System.out.println(problemToPaper);
                paperMapper.addPaper(problemToPaper);
            }
        }
        count = 0;
        chosenIndex.clear();
        while (count < exam.getMultipleNum()) {
            int index = random.nextInt(multipleList.size());
            if (!chosenIndex.contains(index)) {
                chosenIndex.add((long)index);
                count++;
                ProblemToPaper problemToPaper = new ProblemToPaper();
                problemToPaper.setProblem_id(multipleList.get(index).getId());
                problemToPaper.setExam_id(exam.getId());
                paperMapper.addPaper(problemToPaper);
            }
        }
        chosenIndex.clear();
        count = 0;
        while (count < exam.getTfNum()) {
            int index = random.nextInt(tfList.size());
            if (!chosenIndex.contains(index)) {
                chosenIndex.add((long)index);
                count++;
                ProblemToPaper problemToPaper = new ProblemToPaper();
                problemToPaper.setProblem_id(tfList.get(index).getId());
                problemToPaper.setExam_id(exam.getId());
                paperMapper.addPaper(problemToPaper);
            }
        }
        return new ResponseResult(200, "组卷成功!");
    }

    @Override
    public ResponseResult addExamByGeneticAlgorithm(RuleBean ruleBean) {
        ProblemFilter problemFilter = new ProblemFilter();
        List<String> sourceList = new ArrayList<>();
        sourceList.add(ruleBean.getSource());
        problemFilter.setSourceList(sourceList);
        List<Problem> singleList = problemMapper.getProblemByFilter(problemFilter);
        List<Problem> multipleList = problemMapper.getProblemByFilter(problemFilter);
        List<Problem> tfList = problemMapper.getProblemByFilter(problemFilter);
        if (singleList.size() < ruleBean.getSingleNum()) {
            return new ResponseResult(400, "单选题数量不够!");
        } else if (multipleList.size() < ruleBean.getMultipleNum()) {
            return new ResponseResult(400, "多选题数量不够!");
        } else if (tfList.size() < ruleBean.getTfNum()) {
            return new ResponseResult(400, "判断题数量不够!");
        }
        System.out.println(ruleBean);
        int count = 0, runCount = 10;
        ruleBean.setFitness(0.98);
        Population population = new Population(20, true, ruleBean);

        Paper resultPaper = null;

        System.out.println("初次适应度  " + population.getFitness().getAdaptationDegree());
        while (count < runCount && population.getFitness().getAdaptationDegree() < ruleBean.getFitness()) {
            count++;
            population = GeneticAlgorithm.evolvePopulation(population, ruleBean);
            System.out.println("第 " + count + " 次进化，适应度为： " + population.getFitness().getAdaptationDegree());
        }
        System.out.println("进化次数： " + count);
        System.out.println(population.getFitness().getAdaptationDegree());
        resultPaper = population.getFitness();
        List<Problem> questionList = resultPaper.getQuestionList();
        for (int i = 0; i < questionList.size(); i++) {
            ProblemToPaper problemToPaper = new ProblemToPaper();
            problemToPaper.setExam_id(ruleBean.getExam_id());
            problemToPaper.setProblem_id(questionList.get(i).getId());
            paperMapper.addPaper(problemToPaper);
        }
        return new ResponseResult(200, "组卷成功!");
    }
}
