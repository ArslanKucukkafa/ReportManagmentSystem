package com.example.reportmanagmentsystem.repository;

import com.example.reportmanagmentsystem.model.Laborant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LaborantRepository extends JpaRepository<Laborant,Integer> {
    @Query("SELECT l from Laborant l WHERE l.isEnabled=?1")
    List<Laborant>findLaborantsByEnabledIs(boolean activate);
    Optional<Laborant> findByLaborantId(String laborant_id);

    @Query("SELECT l from Laborant l JOIN l.roles r where r.roleName=?1")
    List<Laborant> findAll(String role);

    @Modifying
    @Query("UPDATE Laborant l SET l.isEnabled =?1 where l.laborantId=?2")
    void updateByLaborantId(Boolean activated,String lalaborantId);

    void deleteByLaborantId(String laborant_id);


}
