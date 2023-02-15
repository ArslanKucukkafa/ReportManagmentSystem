package com.example.reportmanagmentsystem.repository;

import com.example.reportmanagmentsystem.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ReportRepository extends JpaRepository<Report,Long> {
    Optional<Report> findById(Long report_id);
    @Modifying
    @Query("update Report r set r.dfnTitle = ?1,r.dfnImgPath = ?2, r.dfnDetails = ?3 where r.reportId = ?4")
    void updateReport(String dfn_title,String dfn_img_path, String dfn_details,Long report_id);
    @Query("SELECT r FROM Report r WHERE r.patient_identity_no=?1 and r.laborant=?2")
    void getAllPatientReports(String patient_identity_no,int laborant_id);

/*    @Query("SELECT l from Report l Where l.patient_identity_no = :patient_identity_no and l.laborant= :laborant_id")
    List<Report> getAllPatientReports(@Param("patient_identity_no")String patient_identity_no,@Param("laborant_id")Integer laborant_id);*/
}
