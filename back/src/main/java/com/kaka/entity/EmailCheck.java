package com.kaka.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailCheck {
    private Long id;
    private String code;
    private String email;
}
