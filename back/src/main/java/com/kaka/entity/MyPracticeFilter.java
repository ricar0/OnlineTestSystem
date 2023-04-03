package com.kaka.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyPracticeFilter {
    private int pageSize;
    private int start;
    private int user_id;
}
