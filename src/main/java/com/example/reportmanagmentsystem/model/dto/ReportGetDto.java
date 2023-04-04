package com.example.reportmanagmentsystem.model.dto;

import com.example.reportmanagmentsystem.model.Laborant;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;


@Data
public class ReportGetDto {
    private Long reportId;
    private String patient_firstname;
    private String patient_lastname;
    private String patient_identity_no;
    private String dfnTitle;
    private String dfnDetails;
    private LocalDateTime create_date;
}
