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
    private int singleNum;
    private int multipleNum;
    private int tfNum;
    private int singleScore;
    private int multipleScore;
    private int tfScore;

}
