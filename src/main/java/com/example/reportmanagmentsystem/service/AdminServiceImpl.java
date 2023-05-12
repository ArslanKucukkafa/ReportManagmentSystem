package com.example.reportmanagmentsystem.service;

import com.example.reportmanagmentsystem.model.Laborant;
import com.example.reportmanagmentsystem.model.Report;
import com.example.reportmanagmentsystem.model.Role;
import com.example.reportmanagmentsystem.model.dto.DetailsDto;
import com.example.reportmanagmentsystem.model.response.ErrorResponse;
import com.example.reportmanagmentsystem.model.response.Response;
import com.example.reportmanagmentsystem.model.response.SuccesResponse;
import com.example.reportmanagmentsystem.repository.LaborantRepository;
import com.example.reportmanagmentsystem.repository.ReportRepository;
import com.example.reportmanagmentsystem.repository.RoleRepository;
import com.example.reportmanagmentsystem.service.interfaces.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final LaborantRepository laborantRepository;
    private final RoleRepository roleRepository;
    private final ReportRepository reportRepository;

    private PasswordEncoder encoder;

    @Override
    @Transactional
    public Response laborantAccountActivate(Boolean activated, String laborant_id) {
        laborantRepository.updateByLaborantId(activated,laborant_id);
        return new SuccesResponse("Account is activated",true);}

    @Transactional
    @Override
    public Response deleteLaborant(String laborant_id){
        Optional<Laborant> laborant = laborantRepository.findByLaborantId(laborant_id);
        Optional<Laborant> currentLaborant=getPrincipal();
        if(laborant.isPresent()){
            laborantRepository.deleteByLaborantId(laborant_id,currentLaborant.get().getLaborantId());
            return new SuccesResponse("Laborant deleted successfully",true);
        }else{
            return new SuccesResponse("Laborant not avaliable ",false);
        }
    }

    @Override
    public List<Report> getAllReportsLaboratories(String laborant_id)  throws Exception{
        Optional<Laborant>laborant= laborantRepository.findByLaborantId(laborant_id);
        return reportRepository.findByAllReportWithLaborantId(laborant.get().getId());
    }

    @Override
    public List<Laborant> getAllPerson(boolean activate) {
        return laborantRepository.findLaborantsByEnabledIs(activate);}

    @Override
    public DetailsDto getDetailsLaborant(String laborant_id) {
        Optional<Laborant>laborant=laborantRepository.findByLaborantId(laborant_id);
        DetailsDto details = new DetailsDto(laborant.get(),reportRepository.reportCount(laborant.get().getId()));
        System.out.println("---------");
        System.out.println(details.getLaborantId()+" "+details.getAd());
        return details;
    }

    public Response upgradeRole (String laborant_id){
try {
    Role role = roleRepository.findByRoleName("ADMIN");
    Optional<Laborant> laborant = laborantRepository.findByLaborantId(laborant_id);
    laborant.get().addRole(role);
    Role role1= roleRepository.findByRoleName("LABORANT");
    laborant.get().getRoles().remove(role1);
    laborantRepository.save(laborant.get());
    return new SuccesResponse("role changed successfully",true);
}catch (Exception e){
    return new ErrorResponse(e.toString(),false);
}
    }

    public Optional<Laborant> getPrincipal(){
        String userName;
        Optional<Laborant>laborant=null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            userName = ((UserDetails)principal).getUsername();
            laborant=laborantRepository.findByLaborantId(userName);}
        return laborant;
    }
}
