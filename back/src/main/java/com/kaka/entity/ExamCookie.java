package com.kaka.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExamCookie {
    private Long user_id;
    private Long exam_id;
    private List<Integer> singleAnswer;
    private List<List<Integer>> multipleAnswer;
    private List<Integer> judgeAnswer;
    private int singleNum;
    private int multipleNum;
    private int judgeNum;
    private int minutes;
    private int seconds;
    private List<Boolean> singleisMark;
    private List<Boolean> multipleisMark;
    private List<Boolean> judgeisMark;
    private List<Boolean> singleisClick;
    private List<Boolean> multipleisClick;
    private List<Boolean> judgeisClick;
    private boolean bg_flag;
}
