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

    @Column(name = "patientName")
    private String ad;

    @Column(name="patientSurname")
    private String soyad;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reportId")
    private Long reportId;

    @Column(name = "dfnTitle")
    private String dfnTitle;

    @Column(name = "dfnDetails")
    private String dfnDetails;

    @Column(name = "dfnImgPath")
    private String dfnImgPath;

    @ManyToOne
    @JoinColumn(name = "laborant_id")
    private Laborant laborantId;
}
