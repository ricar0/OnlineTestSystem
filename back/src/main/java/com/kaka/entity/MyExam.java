package com.kaka.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyExam {
    private Long user_id;
    private Long exam_id;
    private Date start_time;
    private Date end_time;
    private int score;
}
