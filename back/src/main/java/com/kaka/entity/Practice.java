package com.kaka.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Practice {
    private Long id;
    private String source;
    private String description;
    private int totalTime;
    private String author;
    private boolean permission;
    private String password;
    private int difficulty;
    private int number;
    private int singleNum;
    private int multipleNum;
    private int tfNum;

    private Long user_id;
}