package com.example.reportmanagmentsystem.model;

import com.example.reportmanagmentsystem.service.ImageUtil;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.io.IOException;

@Entity(name = "Image")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    private int image_id;
    @Column(name = "image_name")
    private String image_name;
    @Column(name = "image_type")
    private String image_type;
    @Column(name = "data")
    private byte []data;


    // create method retun Image object with all variables and input parameters MultiPartFile object
    public static Image createImage(MultipartFile file) throws IOException {
        Image image = new Image();
        image.setImage_name(file.getOriginalFilename());
        image.setImage_type(file.getContentType());
        image.setData(ImageUtil.compressImage(file.getBytes()));
        return image;
    }





}
