package com.example.reportmanagmentsystem.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class LaborantLoginDto {
    private Long laborant_id;
    private String password;
}
