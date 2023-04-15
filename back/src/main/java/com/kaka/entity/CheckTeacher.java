package com.kaka.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CheckTeacher {
    private Long check_id;
    private Long user_id;
    private String time;
    private int state;
    private List<String> material;
    private int start;
    private int pageSize;
}
