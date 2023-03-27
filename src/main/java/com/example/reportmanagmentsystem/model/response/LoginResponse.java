package com.example.reportmanagmentsystem.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse extends Response{
    public String token;
    public String rol;

    public boolean status;
}
