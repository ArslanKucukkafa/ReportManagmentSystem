package com.example.reportmanagmentsystem.repository;

import com.example.reportmanagmentsystem.model.Laborant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LaborantRepository extends JpaRepository<Laborant,Integer> {
    Optional<Laborant> findByLaborantId(String laborant_id);
}
