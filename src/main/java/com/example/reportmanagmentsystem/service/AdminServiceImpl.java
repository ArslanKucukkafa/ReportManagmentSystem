package com.example.reportmanagmentsystem.service;

import com.example.reportmanagmentsystem.model.Laborant;
import com.example.reportmanagmentsystem.model.Report;
import com.example.reportmanagmentsystem.model.Role;
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

    @Override
    public HashMap<String, String> getDetailsLaborant(String laborant_id) {
        Optional<Laborant>laborant=laborantRepository.findByLaborantId(laborant_id);
        int reportCount= reportRepository.findByLaborantIdOrdOrderByCreate_dateAsc(laborant.get().getId()).size();
        HashMap<String,String>map = new HashMap<>();
        Set<Role>role=laborant.get().getRoles();
        String rolename="";
        for (Role r:role){rolename=r.getRoleName();}
        map.put("reportCount", String.valueOf(reportCount));
        map.put("role",rolename);
        return map;
    }

    public Response upgradeRole (String laborant_id){
        Role role = roleRepository.findByRoleName("ADMIN");
        Optional<Laborant> laborant = laborantRepository.findByLaborantId(laborant_id);
        laborant.get().addRole(role);
        laborantRepository.save(laborant.get());
        return new SuccesResponse("role changed successfully",true);
    }



}
