package com.kaka.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Exam {
    private Long id;
    private String source;
    private int code;
    private String description;
    private int totalTime;
    private int totalScore;
    private String teacher;
    private String examDate;
    private boolean lock;
    private String password;
    private int singleScore;
    private int multipleScore;
    private int tfScore;
}
