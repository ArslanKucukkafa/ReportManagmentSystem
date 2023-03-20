package com.example.reportmanagmentsystem.service;

import com.example.reportmanagmentsystem.model.Laborant;
import com.example.reportmanagmentsystem.model.Report;
import com.example.reportmanagmentsystem.model.Role;
import com.example.reportmanagmentsystem.model.dto.RoleDto;
import com.example.reportmanagmentsystem.model.response.ErrorResponse;
import com.example.reportmanagmentsystem.model.response.LoginResponse;
import com.example.reportmanagmentsystem.model.response.Response;
import com.example.reportmanagmentsystem.model.response.SuccesResponse;
import com.example.reportmanagmentsystem.repository.LaborantRepository;
import com.example.reportmanagmentsystem.repository.ReportRepository;
import com.example.reportmanagmentsystem.repository.RoleRepository;
import com.example.reportmanagmentsystem.service.interfaces.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final LaborantRepository laborantRepository;
    private final RoleRepository roleRepository;
    private final ReportRepository reportRepository;

    @Override
    @Transactional
    public Response laborantAccountActivate(Boolean activated, String laborant_id) {
        laborantRepository.updateByLaborantId(activated,laborant_id);
        return new SuccesResponse("Account is activated",true);}

    @Transactional
    @Override
    public Response deleteLaborant(String laborant_id) {
        Optional<Laborant> laborant = laborantRepository.findByLaborantId(laborant_id);
        if(laborant.isPresent()){
            laborantRepository.deleteByLaborantId(laborant_id);
            return new SuccesResponse("Laborant deleted successfully",true);
        }else{
            return new SuccesResponse("Laborant not avaliable ",false);
        }
    }

    @Override
    public List<Report> getAllReportsLaboratories(String laborant_id) {
        Optional<Laborant> laborant = laborantRepository.findByLaborantId(laborant_id);
        return reportRepository.findByLaborantIdOrdOrderByCreate_dateAsc(laborant.get().getId());
    }

    @Override
    public List<Laborant> getAllPerson(boolean activate) {
        return laborantRepository.findLaborantsByEnabledIs(activate);}
    public Response upgradeRole (String laborant_id){
        Role role = roleRepository.findByRoleName("ADMIN");
        Optional<Laborant> laborant = laborantRepository.findByLaborantId(laborant_id);
        laborant.get().addRole(role);
        laborantRepository.save(laborant.get());
        return new SuccesResponse("role changed successfully",true);
    }
}
