package com.example.reportmanagmentsystem.model.dto;

import com.example.reportmanagmentsystem.model.Laborant;
import com.example.reportmanagmentsystem.model.Report;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.Optional;

@Getter
@Setter
public class ReportSaveDto {

    private String patient_firstname;
    private String patient_lastname;
    private String patient_identity_no;
    private String dfnTitle;
    private String dfnDetails;
    private MultipartFile dfnImgPath;

    public Report saveReportDto(ReportSaveDto reportDto, Optional<Laborant> laborant){
        Report report = new Report();
        report.setLaborant(laborant.get());
        report.setAd(reportDto.getPatient_firstname());
        report.setSoyad(reportDto.getPatient_lastname());
        report.setPatient_identity_no(reportDto.getPatient_identity_no());
        report.setDfnTitle(reportDto.getDfnTitle());
        report.setDfnDetails(reportDto.getDfnDetails());
        String filename= StringUtils.cleanPath(reportDto.getDfnImgPath().getOriginalFilename());
        report.setDfnImgPath(reportDto.getDfnImgPath());
        report.setCreate_date(LocalDateTime.now());
        return report;
    }
}
