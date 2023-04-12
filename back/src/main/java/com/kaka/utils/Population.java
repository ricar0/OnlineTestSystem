package com.kaka.utils;

import com.kaka.entity.Paper;
import com.kaka.entity.Problem;
import com.kaka.entity.RuleBean;
import com.kaka.service.Impl.QuestionService;

import java.util.List;
import java.util.Random;

public class Population {

    private Paper[] papers;

    public Population(int populationSize, boolean initFlag, RuleBean rule) {
        papers = new Paper[populationSize];
        if (initFlag) {
            Paper paper;
            Random random = new Random();
            for (int i = 0; i < populationSize; i++) {
                paper = new Paper();
                paper.setId(i + 1);
                while (paper.getTotalScore(rule) != rule.getTotalScore()) {
                    paper.getQuestionList().clear();
                    // 单选题
                    if (rule.getSingleNum() > 0) {
                        generateQuestion(1, random, rule.getSingleNum(), rule.getDifficulty(), rule.getPointIds(),
                                "单选题数量不够，组卷失败", paper, rule.getSource());
                    }
                    // 多选题
                    if (rule.getMultipleNum() > 0) {
                        generateQuestion(2, random, rule.getMultipleNum(), rule.getDifficulty(), rule.getPointIds(),
                                "填空题数量不够，组卷失败", paper, rule.getSource());
                    }
                    // 判断题
                    if (rule.getTfNum() > 0) {
                        generateQuestion(3, random, rule.getTfNum(), rule.getDifficulty(), rule.getPointIds(),
                                "主观题数量不够，组卷失败", paper, rule.getSource());
                    }
                }
                // 计算试卷知识点覆盖率
                paper.setKpCoverage(rule);
                // 计算试卷适应度
                paper.setAdaptationDegree(rule);
                papers[i] = paper;
            }
        }
    }
    private void generateQuestion(int type, Random random, int qustionNum, double difficulty, List<String> coverage,
                                  String errorMsg, Paper paper, String source) {
        List<Problem> list = QuestionService.getQuestionArray(type, coverage, source, difficulty);
        Problem[] singleArray = list.toArray(new Problem[list.size()]);
        if (singleArray.length < qustionNum) {
            return;
        }
        Problem tmpQuestion;
        for (int j = 0; j < qustionNum; j++) {
            int index = random.nextInt(singleArray.length - j);
            // 初始化分数
//            singleArray[index].setScore(score);
            paper.addQuestion(singleArray[index]);
            // 保证不会重复添加试题
            tmpQuestion = singleArray[singleArray.length - j - 1];
            singleArray[singleArray.length - j - 1] = singleArray[index];
            singleArray[index] = tmpQuestion;
        }
    }

    /**
     * 获取种群中最优秀个体
     *
     * @return
     */
    public Paper getFitness() {
        Paper paper = papers[0];
        for (int i = 1; i < papers.length; i++) {
            if (paper.getAdaptationDegree() < papers[i].getAdaptationDegree()) {
                paper = papers[i];
            }
        }
        return paper;
    }

    public Population(int populationSize) {
        papers = new Paper[populationSize];
    }

    /**
     * 获取种群中某个个体
     *
     * @param index
     * @return
     */
    public Paper getPaper(int index) {
        return papers[index];
    }

    /**
     * 设置种群中某个个体
     *
     * @param index
     * @param paper
     */
    public void setPaper(int index, Paper paper) {
        papers[index] = paper;
    }

    /**
     * 返回种群规模
     *
     * @return
     */
    public int getLength() {
        return papers.length;
    }

}
