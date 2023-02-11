package com.example.reportmanagmentsystem.repository;

import com.example.reportmanagmentsystem.model.Laborant;
import com.example.reportmanagmentsystem.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReportRepository extends JpaRepository<Report,Long> {
    @Override
    Optional<Report> findById(Long report_id);

/*    @Query("SELECT l from Report l Where l.laborant_id= :laborant_id")
    public Optional<Laborant>getAllByLaborantid(@Param("laborant_id")String laborant_id);®*/
}
