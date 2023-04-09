package com.kaka;

import com.kaka.entity.*;
import com.kaka.mapper.ProblemMapper;
import com.kaka.service.GeneticAlgorithm;
import com.kaka.service.UserService;
import com.kaka.utils.RedisCache;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;


import javax.sql.DataSource;
import java.text.SimpleDateFormat;
import java.util.*;

@SpringBootTest
class SecurityApplicationTests {

    @Autowired
    private UserService userService;

    @Autowired(required = false)
    private DataSource dataSource;

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test // 测试数据库连接
    void dataSource() throws Exception {
        System.out.println(dataSource.getConnection());
    }

    @Test
    void getAll() {
        System.out.println(userService.listUser());
    }

    @Test
    void addUser() {
        User user = new User();
        user.setUsername("root");
        user.setPassword("123123");
        userService.addUser(user);
    }

    @Autowired
    private ProblemMapper problemMapper;
    @Test
    void ttt() {
        List<String> coverage = new ArrayList<>();
        coverage.add("1");
        coverage.add("2");
        coverage.add("3");
        coverage.add("4");
        coverage.add("5");
        ProblemFilter problemFilter = new ProblemFilter();
        problemFilter.setCategoryList(coverage);
        List<String> sourceList = new ArrayList<>();
        sourceList.add("思想道德基础和法律修养");
        problemFilter.setSourceList(sourceList);
        List<String> labelList = new ArrayList<>();
        labelList.add("single");
        problemFilter.setLabelList(labelList);
        System.out.println(problemMapper.getProblemByFilter(problemFilter));

    }

   @Test
    void aaa() {
       Paper resultPaper = null;
       // 迭代计数器
       int count = 0;
       int runCount = 100;
       // 适应度期望值
       double expand = 0.98;
       // 可自己初始化组卷规则rule
       RuleBean rule = new RuleBean();
       rule.setSource("思想道德基础和法律修养");
       rule.setTotalScore(100);
       rule.setTfNum(10);
       rule.setMultipleNum(14);
       rule.setSingleNum(10);
       rule.setSingleScore(2);
       rule.setMultipleScore(5);
       rule.setTfScore(1);
       rule.setDifficulty(1.5);
       rule.setCoverageWeight(0.2);
       rule.setDifficultyWeight(0.8);
       rule.setFitness(0.98);
       rule.setPointIds("1,2,3,4,5");
       System.out.println(rule.getPointIds());
       if (rule != null) {
           // 初始化种群
           Population population = new Population(20, true, rule);
           System.out.println("初次适应度  " + population.getFitness().getAdaptationDegree());
           while (count < runCount && population.getFitness().getAdaptationDegree() < expand) {
               count++;
               population = GeneticAlgorithm.evolvePopulation(population, rule);
               System.out.println("第 " + count + " 次进化，适应度为： " + population.getFitness().getAdaptationDegree());
           }
           System.out.println("进化次数： " + count);
           System.out.println(population.getFitness().getAdaptationDegree());
           resultPaper = population.getFitness();
       }
       System.out.println(resultPaper);
   }
}
