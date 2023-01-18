package com.example.reportmanagmentsystem.model.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResponse extends Response{
    private String token;
}
