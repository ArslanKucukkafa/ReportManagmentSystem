package com.example.reportmanagmentsystem.service;

import com.example.reportmanagmentsystem.model.dto.RoleDto;
import com.example.reportmanagmentsystem.model.response.ErrorResponse;
import com.example.reportmanagmentsystem.model.response.LoginResponse;
import com.example.reportmanagmentsystem.model.response.Response;
import com.example.reportmanagmentsystem.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminService {


    private final RoleRepository roleRepository;

    public Response saveRole(RoleDto roleDto){
        try {
            roleRepository.save(roleDto.registerRole(roleDto));
            return new LoginResponse("Role Kayıt Edildi");
        }
        catch (Exception exception){
            return new ErrorResponse(String.valueOf(exception),false);
        }
    }


}
