package com.example.reportmanagmentsystem.service;

import com.example.reportmanagmentsystem.config.security.JwtTokenUtil;
import com.example.reportmanagmentsystem.model.Laborant;
import com.example.reportmanagmentsystem.model.Report;
import com.example.reportmanagmentsystem.model.Role;
import com.example.reportmanagmentsystem.model.dto.LaborantLoginDto;
import com.example.reportmanagmentsystem.model.dto.LaborantRegisterDto;
import com.example.reportmanagmentsystem.model.dto.ReportDto;
import com.example.reportmanagmentsystem.model.dto.ReportSaveDto;
import com.example.reportmanagmentsystem.model.response.ErrorResponse;
import com.example.reportmanagmentsystem.model.response.LoginResponse;
import com.example.reportmanagmentsystem.model.response.Response;
import com.example.reportmanagmentsystem.model.response.SuccesResponse;
import com.example.reportmanagmentsystem.repository.LaborantRepository;
import com.example.reportmanagmentsystem.repository.ReportRepository;
import com.example.reportmanagmentsystem.repository.RoleRepository;
import com.example.reportmanagmentsystem.service.interfaces.LaborantService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LaborantServiceImpl implements  LaborantService {

private final Logger logger = LoggerFactory.getLogger(LaborantServiceImpl.class);
@Autowired
private final LaborantRepository laborantRepository;
private final RoleRepository roleRepository;

private final ReportRepository reportRepository;
private final AuthenticationManager authenticationManager;
private final JwtTokenUtil jwtTokenUtil;


    @Autowired
    private PasswordEncoder encoder;
@Override
public void registerLaborant(LaborantRegisterDto registerDto){
    registerDto.setPassword(encoder.encode(registerDto.getPassword()));
    Laborant laborant = registerDto.registerLaborantDto(registerDto);
    Role role = roleRepository.findByRoleName("ADMIN");
    laborant.setRoles(new HashSet<>());
    laborant.getRoles().add(role);
    System.out.println(registerDto.getPassword());
    laborantRepository.save(laborant);
    }
@Override
public Response loginLaborant(LaborantLoginDto loginDto) throws AuthenticationException {
    Authentication  authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getLaborant_id(),loginDto.getPassword()));
    Optional<Laborant> isConfirmed = laborantRepository.findByLaborantId(loginDto.getLaborant_id());
    if(isConfirmed.isPresent()){
        String token = jwtTokenUtil.generateToken(authentication);
        return LoginResponse.builder().token(token).build();
    }
    else {
        return new ErrorResponse("Invalid password or laborant ıd",false);}
    }

    @Override
    public Response saveReport(ReportSaveDto reportSaveDto){
        Optional<Laborant> laborant = getPrincipal();
        if (laborant.isPresent()){
            reportRepository.save(reportSaveDto.saveReportDto(reportSaveDto,laborant));
            return new SuccesResponse("Report successfullf saved",true);
        }
        else {
            return new ErrorResponse("Error is occured when to save report",false);
        }
    }
    @Override
    public Optional<Laborant> getPrincipal(){
        String userName = null;
        Optional<Laborant>laborant=null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            userName = ((UserDetails)principal).getUsername();
            laborant=laborantRepository.findByLaborantId(userName);
        }
        return laborant;
    }
    @Override
    public List<Report> getAllReports(){
        return reportRepository.findAll();
    }
    @Override
    public List<Report> getAllReportsWithAboutPatient(String patient_identity_no) {
    return reportRepository.getAllPatientReports(patient_identity_no, getPrincipal().get().getId());
    }

    @Override
    @Transactional
    public Response updateReport(ReportDto reportDto){

    try {
        reportRepository.updateReport(reportDto.getDfnTitle(),reportDto.getDfnImgPath(), reportDto.getDfnDetails(),reportDto.getReportId());
    } catch (Exception e){
        System.out.println(e);
        System.out.println("Exception");}
        return new SuccesResponse("Update processing is sucessfully",true);
    }

    @Override
    @Transactional
    public Response deleteReport(Long report_id){
        reportRepository.deleteByReportIdAndLaborantId(report_id,getPrincipal().get().getId());
        return new SuccesResponse("Report deleted successfully",true);
    }
}
