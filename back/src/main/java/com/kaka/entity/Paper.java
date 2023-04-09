package com.kaka.entity;

import com.kaka.entity.Problem;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Paper {
    /**
     * 个体id
     */
    private int id;
    /**
     * 适应度
     */
    private double adaptationDegree = 0.00;
    /**
     * 知识点覆盖率
     */
    private double kPCoverage = 0.00;
    /**
     * 试卷总分
     */
    private double totalScore = 0.00;
    /**
     * 试卷难度系数
     */
    private double difficulty = 0.00;
    /**
     * 个体包含的试题集合
     */
    private List<Problem> questionList = new ArrayList<>();

    public Paper(int size) {
        for (int i = 0; i < size; i++) {
            questionList.add(null);
        }
    }

    public Paper() {
        super();
    }

    /**
     * 计算试卷总分
     *
     * @return
     */
    public double getTotalScore(RuleBean rule) {
        if (totalScore == 0) {
            double total = 0;
            for (Problem question : questionList) {
                if (question.getLabel().equals("single"))
                    total += rule.getSingleScore();
                else if (question.getLabel().equals("multiple"))
                    total += rule.getMultipleScore();
                else
                    total += rule.getTfScore();
            }
            totalScore = total;
        }
        return totalScore;
    }

    /**
     * 计算试卷个体难度系数 计算公式： 每题难度*分数求和除总分
     *
     * @return
     */
    public double getDifficulty(RuleBean rule) {
        if (difficulty == 0) {
            double _difficulty = 0;
            for (Problem question : questionList) {
                if (question.getLabel().equals("single"))
                    _difficulty += rule.getSingleScore() * question.getDifficulty();
                else if (question.getLabel().equals("multiple"))
                    _difficulty += rule.getMultipleScore() * question.getDifficulty();
                else
                    _difficulty += rule.getTfScore() * question.getDifficulty();
            }
            difficulty = _difficulty / rule.getTotalScore();
        }
        return difficulty;
    }

    /**
     * 获取试题数量
     *
     * @return
     */
    public int getQuestionSize() {
        return questionList.size();
    }

    /**
     * 计算知识点覆盖率 公式为：个体包含的知识点/期望包含的知识点
     *
     * @param rule
     */
    public void setKpCoverage(RuleBean rule) {
        if (kPCoverage == 0) {
            Set<String> result = new HashSet<String>();
            result.addAll(rule.getPointIds());
            Set<String> another = questionList.stream().map(questionBean -> String.valueOf(questionBean.getCategory())).collect(Collectors.toSet());
            // 交集操作
            result.retainAll(another);
            kPCoverage = result.size() *1.0 / rule.getPointIds().size();
        }
    }

    /**
     * 计算个体适应度 公式为：f=1-(1-M/N)*f1-|EP-P|*f2
     * 其中M/N为知识点覆盖率，EP为期望难度系数，P为种群个体难度系数，f1为知识点分布的权重
     * ，f2为难度系数所占权重。当f1=0时退化为只限制试题难度系数，当f2=0时退化为只限制知识点分布
     *
     * @param rule 组卷规则
     */
    public void setAdaptationDegree(RuleBean rule) {
        if (adaptationDegree == 0) {
            adaptationDegree = 1 - (1 - getkPCoverage()) * rule.getCoverageWeight() - Math.abs(rule.getDifficulty() - getDifficulty(rule)) * rule.getDifficultyWeight();
        }
    }

    public boolean containsQuestion(Problem question) {
        if (question == null) {
            for (int i = 0; i < questionList.size(); i++) {
                if (questionList.get(i) == null) {
                    return true;
                }
            }
        } else {
            for (Problem aQuestionList : questionList) {
                if (aQuestionList != null) {
                    if (aQuestionList.equals(question)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * 增加问题
     *
     * @param question
     */
    public void saveQuestion(int index, Problem question) {
        this.questionList.set(index, question);
        this.totalScore = 0;
        this.adaptationDegree = 0;
        this.difficulty = 0;
        this.kPCoverage = 0;
    }

    public void addQuestion(Problem question) {
        this.questionList.add(question);
        this.totalScore = 0;
        this.adaptationDegree = 0;
        this.difficulty = 0;
        this.kPCoverage = 0;
    }

    public Problem getQuestion(int index) {
        return questionList.get(index);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getkPCoverage() {
        return kPCoverage;
    }

    public double getAdaptationDegree() {
        return adaptationDegree;
    }

    public List<Problem> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<Problem> questionList) {
        this.questionList = questionList;
    }

}