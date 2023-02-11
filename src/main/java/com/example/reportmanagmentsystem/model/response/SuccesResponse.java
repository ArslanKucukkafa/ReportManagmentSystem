package com.example.reportmanagmentsystem.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SuccesResponse extends Response {
    public String message;
    public boolean status;
}
