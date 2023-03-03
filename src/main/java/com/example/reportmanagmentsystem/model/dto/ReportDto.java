package com.example.reportmanagmentsystem.model.dto;

import com.example.reportmanagmentsystem.model.Report;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReportDto {
    private Long reportId;
    private String ad;
    private String soyad;
    private String patient_identity_no;
    private String dfnTitle;
    private String dfnDetails;
    private String dfnImgPath;
    private LocalDateTime create_date;

}
