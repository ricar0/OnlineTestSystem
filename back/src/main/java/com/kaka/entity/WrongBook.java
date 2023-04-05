package com.kaka.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WrongBook {
    private Long user_id;
    private Long problem_id;
    private String last_submit;
    private int accept_num;
    private int wrong_num;
}
