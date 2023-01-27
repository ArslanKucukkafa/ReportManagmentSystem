package com.example.reportmanagmentsystem.model.dto;

import com.example.reportmanagmentsystem.model.Laborant;
import com.example.reportmanagmentsystem.model.Report;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReportSaveDto {

    private String patient_firstname;
    private String patient_lastname;
    private String patient_identity_no;
    private String dfnTitle;
    private String dfnDetails;
    private String dfnImgPath;

    public Report saveReportDto(ReportSaveDto reportDto, Laborant laborant){
        Report report = new Report();
        report.setAd(reportDto.getPatient_firstname());
        report.setSoyad(reportDto.getPatient_lastname());
        report.setLaborantid(laborant);
        return report;
    }
}
