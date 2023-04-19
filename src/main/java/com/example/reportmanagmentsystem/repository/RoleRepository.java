package com.example.reportmanagmentsystem.repository;

import com.example.reportmanagmentsystem.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,String> {
    Role  findByRoleName(String roleName);

}
