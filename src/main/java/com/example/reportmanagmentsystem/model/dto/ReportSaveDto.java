package com.example.reportmanagmentsystem.model.dto;

import com.example.reportmanagmentsystem.model.Laborant;
import com.example.reportmanagmentsystem.model.Report;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Optional;

@Getter
@Setter
public class ReportSaveDto {

    private String patient_firstname;
    private String patient_lastname;
    private String patient_identity_no;
    private String dfnTitle;
    private String dfnDetails;

    public String imagesConvertor(MultipartFile file) throws IOException{
        String filename= StringUtils.cleanPath(file.getOriginalFilename());
        if(filename.contains(".."))
        {
            System.out.println("not a a valid file");
        }
           return Base64.getEncoder().encodeToString(file.getBytes());

    }

    public Report saveReportDto(ReportSaveDto reportDto, Optional<Laborant> laborant,MultipartFile file) throws Exception{
        Report report = new Report();
        report.setLaborant(laborant.get());
        report.setAd(reportDto.getPatient_firstname());
        report.setSoyad(reportDto.getPatient_lastname());
        report.setPatient_identity_no(reportDto.getPatient_identity_no());
        report.setDfnTitle(reportDto.getDfnTitle());
        report.setDfnDetails(reportDto.getDfnDetails());
        report.setDfnImgPath(imagesConvertor(file));
        report.setCreate_date(LocalDateTime.now());
        return report;
    }
}
