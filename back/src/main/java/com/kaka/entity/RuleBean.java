package com.kaka.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RuleBean {
    private String source;
    private int tfScore;
    private int multipleScore;
    private int singleScore;
    private int tfNum;
    private int multipleNum;
    private int singleNum;
    private int totalScore;
    private double difficulty;
    private double fitness;
    private int totalNum;
    private List<String> pointIds;
    private double coverageWeight;
    private double difficultyWeight;

    private String teacher;
    private String description;
    private String start_time;
    private String end_time;
    private Boolean permission;
    private String password;
    private int totalTime;
    private Long exam_id;
}
