package com.example.reportmanagmentsystem.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "report")
@Data
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "report_id")
    private Long reportId;
    @Column(name = "patient_firstname")
    private String patient_firstname;
    @Column(name="patient_lastname")
    private String patient_lastname;
    @Column(name = "patient_identity_no")
    private String patient_identity_no;
    @Column(name = "dfn_title")
    private String dfnTitle;
    @Column(name = "dfn_Details")
    private String dfnDetails;
    @Column(name = "image_data")
    private byte [] image;
    @ManyToOne
    @JsonIgnore
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "laborant_id",nullable = false)
    private Laborant laborant;
    @Column(name = "create_date")
    private LocalDateTime create_date;
}
