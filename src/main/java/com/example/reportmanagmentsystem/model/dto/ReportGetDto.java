package com.example.reportmanagmentsystem.model.dto;

import com.example.reportmanagmentsystem.model.Report;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportGetDto {
    private Long reportId;
    private String patient_firstname;
    private String patient_lastname;
    private String patient_identity_no;
    private String dfnTitle;
    private String dfnDetails;
    private LocalDateTime create_date;


    // create method for ReportGetDto contain all variable return ReportGetDto and input parameter is Report object
    public ReportGetDto (Report report){
        this.patient_firstname=report.getPatient_firstname();
        this.patient_lastname=report.getPatient_lastname();
        this.reportId=report.getReportId();
        this.dfnDetails=report.getDfnDetails();
        this.dfnTitle=report.getDfnTitle();
        this.create_date=report.getCreate_date();
        this.patient_identity_no=report.getPatient_identity_no();
    }
}

