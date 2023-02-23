package com.example.reportmanagmentsystem.repository;

import com.example.reportmanagmentsystem.model.Laborant;
import com.example.reportmanagmentsystem.model.Role;
import com.example.reportmanagmentsystem.model.response.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface LaborantRepository extends JpaRepository<Laborant,Integer> {
    Optional<Laborant> findByLaborantId(String laborant_id);

    @Query("SELECT l from Laborant l JOIN l.roles r where r.roleName=?1")
    List<Laborant> findAll(String role);

    @Query("UPDATE Laborant l SET l.isEnabled =?1 where l.laborantId=?2")
    void updateByLaborantId(Boolean activated,String lalaborantId);

    public void deleteByLaborantId(String laborant_id);
}
