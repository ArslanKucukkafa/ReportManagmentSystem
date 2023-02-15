package com.example.reportmanagmentsystem.service.interfaces;

import com.example.reportmanagmentsystem.model.Laborant;
import com.example.reportmanagmentsystem.model.Role;
import com.example.reportmanagmentsystem.model.dto.RoleDto;
import com.example.reportmanagmentsystem.model.response.Response;

public interface AdminService {

    Response saveRole(RoleDto roleDto);

    Response putRoleToLaborant(Laborant laborant);

    Response deletePerson(Laborant laborant);

    Response updatePerson(Laborant laborant);

    Response updateRole(Role role);

    Response deleteRole(Role role);

    Response findRole(Role role);

    Response findPerson(Laborant laborant);

    Response findAllPerson();
}
