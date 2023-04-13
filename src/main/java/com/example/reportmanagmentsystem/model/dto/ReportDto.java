package com.example.reportmanagmentsystem.model.dto;

import com.example.reportmanagmentsystem.model.Image;
import com.example.reportmanagmentsystem.model.Report;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.Set;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReportDto {
    private Long reportId;
    private String patient_firstname;
    private String patient_lastname;
    private String patient_identity_no;
    private String dfnTitle;
    private String dfnDetails;
    private Image image;
    private LocalDateTime create_date;

    public ReportDto(Report report){
        this.patient_firstname=report.getPatient_firstname();
        this.patient_lastname=report.getPatient_lastname();
        this.reportId=report.getReportId();
        this.dfnDetails=report.getDfnDetails();
        this.dfnTitle=report.getDfnTitle();
        this.create_date=report.getCreate_date();
        this.image=report.getImage();
        this.patient_identity_no=report.getPatient_identity_no();}



}
