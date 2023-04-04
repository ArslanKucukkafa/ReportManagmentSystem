package com.example.reportmanagmentsystem.service;

import com.example.reportmanagmentsystem.config.security.JwtTokenUtil;
import com.example.reportmanagmentsystem.model.Laborant;
import com.example.reportmanagmentsystem.model.Report;
import com.example.reportmanagmentsystem.model.Role;
import com.example.reportmanagmentsystem.model.dto.*;
import com.example.reportmanagmentsystem.model.response.ErrorResponse;
import com.example.reportmanagmentsystem.model.response.LoginResponse;
import com.example.reportmanagmentsystem.model.response.Response;
import com.example.reportmanagmentsystem.model.response.SuccesResponse;
import com.example.reportmanagmentsystem.repository.LaborantRepository;
import com.example.reportmanagmentsystem.repository.ReportRepository;
import com.example.reportmanagmentsystem.repository.RoleRepository;
import com.example.reportmanagmentsystem.service.interfaces.LaborantService;
import lombok.RequiredArgsConstructor;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LaborantServiceImpl implements  LaborantService {

@Autowired
private final LaborantRepository laborantRepository;
private final RoleRepository roleRepository;

private final ReportRepository reportRepository;
private final AuthenticationManager authenticationManager;
private final JwtTokenUtil jwtTokenUtil;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder encoder;
@Override
public void registerLaborant(LaborantRegisterDto registerDto){
    if(laborantRepository.findByLaborantId(registerDto.getLaborant_id()).isPresent()){
    }else{
        registerDto.setPassword(encoder.encode(registerDto.getPassword()));
        Laborant laborant = registerDto.registerLaborantDto(registerDto);
        Role role = roleRepository.findByRoleName("LABORANT");
        laborant.setRoles(new HashSet<>());
        laborant.getRoles().add(role);
        System.out.println(registerDto.getPassword());
        laborantRepository.save(laborant);
    }


    }
@Override
public Response loginLaborant(LaborantLoginDto loginDto) throws AuthenticationException {
    Authentication  authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getLaborant_id(),loginDto.getPassword()));
    Optional<Laborant> isConfirmed = laborantRepository.findByLaborantId(loginDto.getLaborant_id());
    if(isConfirmed.isPresent()){
        List<Role>roles=new ArrayList<Role>(isConfirmed.get().getRoles());
        String token = jwtTokenUtil.generateToken(authentication);
        System.out.println(isConfirmed.get().getRoles());
        return new LoginResponse(token,roles.get(0).getRoleName(),true);
    }
    else {
        return new ErrorResponse("Invalid password or laborant ıd",false);}
    }

    @Override
    public Response saveReport(ReportSaveDto reportSaveDto,MultipartFile image) throws Exception {
        Optional<Laborant> laborant = getPrincipal();
        if (laborant.isPresent()){
            reportRepository.save(reportSaveDto.saveReportDto(reportSaveDto,image,laborant));
            return new SuccesResponse("Report successfullf saved",true);
        }
        else {
            return new ErrorResponse("Error is occured when to save report",false);
        }
    }

    @Override
    public Optional<Laborant> getPrincipal(){
        String userName;
        Optional<Laborant>laborant=null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            userName = ((UserDetails)principal).getUsername();
            laborant=laborantRepository.findByLaborantId(userName);
        }
        return laborant;
    }
    @Override
    public List<ReportGetDto> getAllReports(){
        Optional<Laborant>currentLaborant = getPrincipal();
        return reportRepository.findByAllReportWithLaborantId(currentLaborant.get().getId()).stream().map(post ->modelMapper.map(post,ReportGetDto.class)).collect(Collectors.toList());
    }
    @Override
    public List<ReportGetDto> getAllReportsWithAboutPatient(String patient_identity_no) {
    return reportRepository.getAllPatientReports(patient_identity_no, getPrincipal().get().getId()).stream().map(post ->modelMapper.map(post, ReportGetDto.class)).collect(Collectors.toList());
    }


    @Override
    @Transactional
    public Response updateReport(ReportDto reportDto){

    try {
        reportRepository.updateReport(reportDto.getDfnTitle(),reportDto.getImage(), reportDto.getDfnDetails(),reportDto.getReportId());
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
