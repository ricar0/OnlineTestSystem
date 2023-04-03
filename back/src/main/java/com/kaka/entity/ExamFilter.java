package com.kaka.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExamFilter {
    private List<String> permissionList;
    private String state;
    private String time;
    int pageSize;
    int start;
}
