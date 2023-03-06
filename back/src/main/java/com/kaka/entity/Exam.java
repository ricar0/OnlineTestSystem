package com.kaka.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Exam {
    private Long id;
    private String source;
    private String description;
    private int totalTime;
    private int totalScore;
    private String teacher;
    private String examDate;
    private boolean lock;
}
