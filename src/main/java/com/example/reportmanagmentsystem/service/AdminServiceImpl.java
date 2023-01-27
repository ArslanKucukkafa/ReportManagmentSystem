package com.example.reportmanagmentsystem.service;

import com.example.reportmanagmentsystem.model.Laborant;
import com.example.reportmanagmentsystem.model.Role;
import com.example.reportmanagmentsystem.model.dto.RoleDto;
import com.example.reportmanagmentsystem.model.response.ErrorResponse;
import com.example.reportmanagmentsystem.model.response.LoginResponse;
import com.example.reportmanagmentsystem.model.response.Response;
import com.example.reportmanagmentsystem.repository.LaborantRepository;
import com.example.reportmanagmentsystem.repository.RoleRepository;
import com.example.reportmanagmentsystem.service.interfaces.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final LaborantRepository laborantRepository;
    private final RoleRepository roleRepository;

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

    //  TODO Role update işlemi admin tarafından yapılacak.
    @Override
    public Response putRoleToLaborant(Laborant laborant) {
        return null;
    }

    @Override
    public Response deletePerson(Laborant laborant) {
        return null;
    }

    @Override
    public Response updatePerson(Laborant laborant) {
        return null;
    }

    @Override
    public Response updateRole(Role role) {
        return null;
    }

    @Override
    public Response deleteRole(Role role) {
        return null;
    }

    @Override
    public Response findRole(Role role) {
        return null;
    }


}
