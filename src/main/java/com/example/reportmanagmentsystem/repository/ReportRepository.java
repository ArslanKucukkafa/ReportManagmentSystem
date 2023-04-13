package com.example.reportmanagmentsystem.repository;

import com.example.reportmanagmentsystem.model.Image;
import com.example.reportmanagmentsystem.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface ReportRepository extends JpaRepository<Report,Long> {

    @Query("SELECT r from  Report r where r.reportId=?1 and r.laborant.id=?2")
    Optional<Report> findByIdAndLaborant_Id(Long report_id,Long laborant_id);

    @Modifying
    @Query("update Report r set r.dfnTitle = ?1 , r.image.data=?2,r.image.image_type=?3, r.dfnDetails = ?4 where r.reportId = ?5")
    void updateReport(String dfn_title,byte[] data,String image_type, String dfn_details, Long report_id);
    @Query("SELECT COUNT(r) FROM Report r WHERE r.laborant.id =?1")
    Integer reportCount(Long id);

    @Query("SELECT l from Report l Where l.patient_identity_no = :patient_identity_no and l.laborant.id= :laborant_id")
    List<Report> getAllPatientReports(@Param("patient_identity_no") String patient_identity_no, @Param("laborant_id")Long laborant_id);

    @Query("SELECT r FROM Report r WHERE r.laborant.id=?1 order by r.create_date asc")
    List<Report> findByAllReportWithLaborantId(Long laborant_id);

    void deleteByReportIdAndLaborantId(Long report_id,Long laborant_id);

    //create method update Report with image but input variable ReportGetDto
    @Modifying
    @Query("update Report r set r.dfnTitle = :#{#report.dfnTitle} , r.image.data=:#{#report.image.data},r.image.image_type=:#{#report.image.image_type}, r.dfnDetails = :#{#report.dfnDetails} where r.reportId = :#{#report.reportId}")
    void updateReportWithImage(@Param("report") Report report);

}
