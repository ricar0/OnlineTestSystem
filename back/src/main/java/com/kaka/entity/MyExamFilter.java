package com.kaka.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyExamFilter {
    private int user_id;
    private int exam_id;
    int pageSize;
    int start;
}
