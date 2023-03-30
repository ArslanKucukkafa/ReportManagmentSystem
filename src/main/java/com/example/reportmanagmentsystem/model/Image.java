package com.example.reportmanagmentsystem.model;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.io.IOException;

@Entity
@Table(name = "image")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Image {

    public Image(String image_name ,String image_type,byte[]image_data) throws IOException {
        this.image_name = image_name;
        this.image_type = image_type;
        this.image_data = image_data;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    private int image_id;
    @Column(name = "image_name")
    private String image_name;
    @Column(name = "image_type")
    private String image_type;
    @Column(name = "image_data")
    private byte[] image_data;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "image_id",referencedColumnName = "report_id")
    private Report report;
}
