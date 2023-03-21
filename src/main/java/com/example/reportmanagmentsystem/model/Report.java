package com.example.reportmanagmentsystem.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "report")
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "report_id")
    private Long reportId;

    @Column(name = "patient_name")
    private String ad;

    @Column(name="patient_surname")
    private String soyad;

    @Column(name = "patient_identity_no")
    private String patient_identity_no;

    @Column(name = "dfn_title")
    private String dfnTitle;

    @Column(name = "dfn_Details")
    private String dfnDetails;

    @Lob
    @Column(columnDefinition = "bytea")
    private String dfnImgPath;
    @ManyToOne()
    @JoinColumn(name = "laborant_id")
    private Laborant laborant;
    @Column(name = "create_date")
    private LocalDateTime create_date;
}
