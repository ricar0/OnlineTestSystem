package com.kaka.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PracticeFilter {
    private List<String> sourceList;
    private List<String> difficultyList;
    private List<String> permissionList;
    int pageSize;
    int start;
}
