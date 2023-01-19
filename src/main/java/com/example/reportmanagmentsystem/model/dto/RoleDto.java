package com.example.reportmanagmentsystem.model.dto;

import com.example.reportmanagmentsystem.model.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleDto {
    private String role_name;

    public Role registerRole(RoleDto dto){
        Role role = new Role();
        role.setRoleName(dto.getRole_name());
        return role;
    }

}
