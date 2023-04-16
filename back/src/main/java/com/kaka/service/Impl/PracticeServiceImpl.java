package com.kaka.service.Impl;

import com.kaka.entity.*;
import com.kaka.mapper.PracticeMapper;
import com.kaka.mapper.ProblemMapper;
import com.kaka.service.PracticeService;
import com.kaka.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PracticeServiceImpl implements PracticeService {

    @Autowired
    private PracticeMapper practiceMapper;

    @Autowired
    private ProblemMapper problemMapper;

    @Override
    public Long addPractice(Practice practice) {
        practiceMapper.addPractice(practice);
        return practice.getId();
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

    @Override
    public ResponseResult generateProblems(Practice practice, Long id) {
        ProblemFilter problemFilter1 = new ProblemFilter();
        ProblemFilter problemFilter2 = new ProblemFilter();
        ProblemFilter problemFilter3 = new ProblemFilter();
        List<String> sourceList = new ArrayList<>();
        sourceList.add(practice.getSource());
        problemFilter1.setSourceList(sourceList);
        problemFilter2.setSourceList(sourceList);
        problemFilter3.setSourceList(sourceList);
        List<String> labelList1 = new ArrayList<>();
        List<String> labelList2 = new ArrayList<>();
        List<String> labelList3 = new ArrayList<>();
        labelList1.add("single");
        labelList2.add("multiple");
        labelList3.add("tf");
        problemFilter1.setLabelList(labelList1);
        problemFilter2.setLabelList(labelList2);
        problemFilter3.setLabelList(labelList3);
        if (practice.getDifficulty() != 0) {
            List<String> difficulty = new ArrayList<>();
            difficulty.add(Integer.toString(practice.getDifficulty()));
            problemFilter1.setDifficultyList(difficulty);
            problemFilter2.setDifficultyList(difficulty);
            problemFilter3.setDifficultyList(difficulty);
        }
        List<Problem> singleList = problemMapper.getProblemByFilter(problemFilter1);
        List<Problem> multipleList = problemMapper.getProblemByFilter(problemFilter2);
        List<Problem> tfList = problemMapper.getProblemByFilter(problemFilter3);


        if (singleList.size() < practice.getSingleNum()) {
            return new ResponseResult(400, "单选题数量不够!");
        } else if (multipleList.size() < practice.getMultipleNum()) {
            return new ResponseResult(400, "多选题数量不够!");
        } else if (tfList.size() < practice.getTfNum()) {
            return new ResponseResult(400, "判断题数量不够!");
        }

        Random random = new Random();
        Set<Long> chosenIndex = new HashSet<>();
        int count = 0;
        while (count < practice.getSingleNum()) {
            int index = random.nextInt(singleList.size());
            if (!chosenIndex.contains((long)index)) {
                chosenIndex.add((long)index);
                count++;
                ProblemToPaper problemToPaper = new ProblemToPaper();
                problemToPaper.setProblem_id(singleList.get(index).getId());
                problemToPaper.setExam_id(practice.getId());
                practiceMapper.addProblemToPractice(problemToPaper);
            }
        }
        count = 0;
        chosenIndex.clear();
        while (count < practice.getMultipleNum()) {
            int index = random.nextInt(multipleList.size());
            if (!chosenIndex.contains((long)index)) {
                chosenIndex.add((long)index);
                count++;
                ProblemToPaper problemToPaper = new ProblemToPaper();
                problemToPaper.setProblem_id(multipleList.get(index).getId());
                problemToPaper.setExam_id(practice.getId());
                practiceMapper.addProblemToPractice(problemToPaper);
            }
        }
        chosenIndex.clear();
        count = 0;
        while (count < practice.getTfNum()) {
            int index = random.nextInt(tfList.size());
            if (!chosenIndex.contains((long)index)) {
                chosenIndex.add((long)index);
                count++;
                ProblemToPaper problemToPaper = new ProblemToPaper();
                problemToPaper.setProblem_id(tfList.get(index).getId());
                problemToPaper.setExam_id(practice.getId());
                practiceMapper.addProblemToPractice(problemToPaper);
            }
        }
        return new ResponseResult(200, "组卷成功!");
    }

    @Override
    public Practice getPracticeInfo(Long id) {
        return practiceMapper.getPracticeInfo(id);
    }

    @Override
    public void registerPractice(MyPractice myPractice) {
        practiceMapper.registerPractice(myPractice);
        practiceMapper.addPracticeNumber(myPractice.getPractice_id());
    }

    @Override
    public MyPractice getRegisterState(MyPractice myPractice) {
        return practiceMapper.getRegisterState(myPractice);
    }

    @Override
    public List<Problem> getProblemByPracticeId(Long id) {
        return practiceMapper.getProblemByPracticeId(id);
    }

    @Override
    public void addPracticeResult(ScoreResult scoreResult) {
        practiceMapper.addPracticeResult(scoreResult);
    }

    @Override
    public void deletePractice(Long id) {
        practiceMapper.deletePracticeProblem(id);
        practiceMapper.deletePractice(id);
    }
}
