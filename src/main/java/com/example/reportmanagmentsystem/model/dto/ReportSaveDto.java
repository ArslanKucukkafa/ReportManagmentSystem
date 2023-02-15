package com.example.reportmanagmentsystem.model.dto;

import com.example.reportmanagmentsystem.model.Laborant;
import com.example.reportmanagmentsystem.model.Report;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

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
    private String dfnImgPath;

    public String getPrincipal(){
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            userName = ((UserDetails)principal).getUsername();
        }
        return userName;
    }

    public Report saveReportDto(ReportSaveDto reportDto, Optional<Laborant> laborant){
        Report report = new Report();
        report.setLaborant(laborant.get());
        report.setAd(reportDto.getPatient_firstname());
        report.setSoyad(reportDto.getPatient_lastname());
        report.setPatient_identity_no(reportDto.getPatient_identity_no());
        report.setDfnTitle(reportDto.getDfnTitle());
        report.setDfnDetails(reportDto.getDfnDetails());
        report.setDfnImgPath(reportDto.getDfnImgPath());
        report.setCreate_date(LocalDateTime.now());
        return report;
    }
}
