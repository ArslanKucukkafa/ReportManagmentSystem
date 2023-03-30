package com.example.reportmanagmentsystem.model.dto;

import com.example.reportmanagmentsystem.model.Image;
import com.example.reportmanagmentsystem.model.Laborant;
import com.example.reportmanagmentsystem.model.Report;
import com.example.reportmanagmentsystem.service.ImageUtil;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

    public byte[] imagesConvertor(MultipartFile image) throws IOException{
        System.out.println(image.getBytes().toString());
        return ImageUtil.compressImage(image.getBytes());

    }

    public Report saveReportDto(ReportSaveDto reportDto,MultipartFile image,Optional<Laborant> laborant) throws Exception{
        Report report = new Report();
        report.setLaborant(laborant.get());
        report.setAd(reportDto.getPatient_firstname());
        report.setSoyad(reportDto.getPatient_lastname());
        report.setPatient_identity_no(reportDto.getPatient_identity_no());
        report.setDfnTitle(reportDto.getDfnTitle());
        report.setDfnDetails(reportDto.getDfnDetails());
        report.setImage(Image.builder().image_name(image.getOriginalFilename()).image_type(image.getContentType()).image_data(ImageUtil.compressImage(image.getBytes())).build());
        report.setCreate_date(LocalDateTime.now());
        return report;
    }
}
