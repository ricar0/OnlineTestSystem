package com.kaka.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Problem {
    private String label;
    private String description;
    private String accept;
    private int difficulty;
    private Long id;
    private int option_num;
    private String A;
    private String B;
    private String C;
    private String D;
    private String source;
}
