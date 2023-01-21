package com.example.reportmanagmentsystem.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "report")
public class Report {

    @Column(name = "patient_name")
    private String ad;

    @Column(name="patient_surname")
    private String soyad;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "report_id")
    private Long reportId;

    @Column(name = "dfn_title")
    private String dfnTitle;

    @Column(name = "dfn_Details")
    private String dfnDetails;

    @Column(name = "dfn_img_path")
    private String dfnImgPath;

    @ManyToOne
    @JoinColumn(name = "laborant_id")
    private Laborant laborantId;
}
