package com.example.reportmanagmentsystem.model;


import com.fasterxml.jackson.annotation.JsonFormat;
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

    @ManyToOne
    @JsonIgnore
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "laborant_id",nullable = false)
    private Laborant laborant;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(name = "create_date")
    private LocalDateTime create_date;

    // create set object for image with OneToMany
    @OneToOne (cascade = CascadeType.ALL ,fetch = FetchType.EAGER,orphanRemoval = true)
    @JoinColumn(name = "image_id")
    private Image image;

}
