package com.example.reportmanagmentsystem.service.interfaces;

import com.example.reportmanagmentsystem.model.Laborant;
import com.example.reportmanagmentsystem.model.Report;
import com.example.reportmanagmentsystem.model.dto.*;
import com.example.reportmanagmentsystem.model.response.Response;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface LaborantService {

    void registerLaborant(LaborantRegisterDto registerDto);
     Response loginLaborant(LaborantLoginDto loginDto);

    Response saveReport(ReportSaveDto reportSaveDto,MultipartFile file) throws Exception;

    List<Report> getAllReportsWithAboutPatient(String patient_identity_no);

    Report getReport(Long reportId);

    Response updateReport(ReportGetDto reportGetDto,MultipartFile file) throws Exception;
    Optional<Laborant> getPrincipal();

    List<ReportGetDto> getAllReports();

    Response deleteReport(Long report_id);
}
