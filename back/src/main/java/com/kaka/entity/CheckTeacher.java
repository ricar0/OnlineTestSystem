package com.kaka.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CheckTeacher {
    private Long id;
    private Long user_id;
    private User user;
    private List<String> material;
    private int start;
}
