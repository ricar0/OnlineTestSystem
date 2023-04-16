package com.kaka.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScoreResult {
    private Long user_id;
    private Long exam_id;
    private Long practice_id;
    private float acceptRate;
    private int score;
    private String end_time;
}
