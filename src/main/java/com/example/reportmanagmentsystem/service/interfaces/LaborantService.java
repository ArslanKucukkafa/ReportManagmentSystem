package com.example.reportmanagmentsystem.service.interfaces;

import com.example.reportmanagmentsystem.model.Laborant;
import com.example.reportmanagmentsystem.model.Report;
import com.example.reportmanagmentsystem.model.dto.LaborantLoginDto;
import com.example.reportmanagmentsystem.model.dto.LaborantRegisterDto;
import com.example.reportmanagmentsystem.model.dto.ReportDto;
import com.example.reportmanagmentsystem.model.dto.ReportSaveDto;
import com.example.reportmanagmentsystem.model.response.Response;
import java.util.List;
import java.util.Optional;

public interface LaborantService {

    void registerLaborant(LaborantRegisterDto registerDto);
     Response loginLaborant(LaborantLoginDto loginDto);

    Response saveReport(ReportSaveDto reportSaveDto);

    List<Report> getAllReportsWithAboutPatient(String patient_identity_no);

    Response updateReport(ReportDto reportDto);
    Optional<Laborant> getPrincipal();

    List<Report> getAllReports();

    Response deleteReport(Long report_id);
}
