package com.kaka.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaperBean {
    private List<Problem> problems;
    private Exam exam;
    private Practice practice;
}
