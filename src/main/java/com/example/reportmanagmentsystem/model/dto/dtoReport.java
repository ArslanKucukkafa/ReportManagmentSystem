package com.example.reportmanagmentsystem.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "Report_information")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class dtoReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int report_id;

    @Column(name = "patinetFirstname")
    private String patinetFirstname;

    @Column(name="patinetLastname")
    private String patinetLastname;

    @Column(name = "patient_no")
    private String patient_no;

    //diagnos == tanı ,teşhis
    @Column(name="patient_diagnosis")
    private String patient_diagnosis;

    @Column(name= "diagnosis_details")
    private String diagnosis_details;

}
