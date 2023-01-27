package com.example.reportmanagmentsystem.repository;

import com.example.reportmanagmentsystem.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReportRepository extends JpaRepository<Report,Long> {
    @Override
    Optional<Report> findById(Long report_id);

}
