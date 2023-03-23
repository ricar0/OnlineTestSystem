package com.kaka.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProblemFilter {
    private List<String> sourceList;
    private List<String> difficultyList;
    private List<String> labelList;
    private List<String> source;
    private List<String> difficulty;
    int pageSize;
    int start;
}
