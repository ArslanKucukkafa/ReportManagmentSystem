package com.example.reportmanagmentsystem.service;

import com.example.reportmanagmentsystem.model.Laborant;
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
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final LaborantRepository laborantRepository;
    private final RoleRepository roleRepository;
    private final ReportRepository reportRepository;

    @Override
    public Response saveRole(RoleDto roleDto){
        try {
            roleRepository.save(roleDto.registerRole(roleDto));
            return new LoginResponse("Role Kayıt Edildi");
        }
        catch (Exception exception){
            return new ErrorResponse(String.valueOf(exception),false);
        }
    }


    //TODO Deneme olarak role tablosuna erişim kontrol edilecek
    public List<Laborant> isHaveRole(){
        return laborantRepository.findAll("LABORANT"); }


    @Override
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
    public Response getAllReportsLaboratories(String laborant_id) {
        reportRepository.getReportByLaborant_Id(laborant_id);
        return new SuccesResponse(reportRepository.getReportByLaborant_Id(laborant_id).toString(),true);
    }

    @Override
    public Response getAllPerson() {
        return new SuccesResponse(laborantRepository.findAll().toString(),true);
    }

}
