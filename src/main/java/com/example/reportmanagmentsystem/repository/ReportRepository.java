package com.example.reportmanagmentsystem.repository;

import com.example.reportmanagmentsystem.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReportRepository extends JpaRepository<Report,Long> {
    Optional<Report> findById(Long report_id);

    @Modifying
    @Query("update Report r set r.dfnTitle = ?1,r.image = ?2, r.dfnDetails = ?3 where r.reportId = ?4")
    void updateReport(String dfn_title,byte[] image, String dfn_details,Long report_id);

    @Query("SELECT COUNT(r) FROM Report r WHERE r.laborant.id =?1")
    Integer reportCount(Long id);
    /*    @Query("SELECT r FROM Report r WHERE r.patient_identity_no=?1 and r.laborant.id=?2")
    void getAllPatientReports1(String patient_identity_no,Integer laborant_id);*/
    @Query("SELECT l from Report l Where l.patient_identity_no = :patient_identity_no and l.laborant.id= :laborant_id")
    List<Report> getAllPatientReports(@Param("patient_identity_no") String patient_identity_no, @Param("laborant_id")Long laborant_id);

    @Query("SELECT r FROM Report r WHERE r.laborant.id=?1 order by r.create_date asc")
    List<Report> findByAllReportWithLaborantId(Long laborant_id);

    void deleteByReportIdAndLaborantId(Long report_id,Long laborant_id);
}
