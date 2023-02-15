package com.example.reportmanagmentsystem.repository;

import com.example.reportmanagmentsystem.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface ReportRepository extends JpaRepository<Report,Long> {
    Optional<Report> findById(Long report_id);
    @Modifying
    @Query("update Report r set r.dfnTitle = ?1,r.dfnImgPath = ?2, r.dfnDetails = ?3 where r.reportId = ?4")
    void updateReport(String dfn_title,String dfn_img_path, String dfn_details,Long report_id);

/*    @Query("SELECT l from Report l Where l.laborant_id= :laborant_id")
    public Optional<Laborant>getAllByLaborantid(@Param("laborant_id")String laborant_id);®*/
}
