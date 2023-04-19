package com.example.reportmanagmentsystem.model.dto;

import com.example.reportmanagmentsystem.model.Laborant;
import com.example.reportmanagmentsystem.model.Role;
import lombok.Data;
@Data
public class DetailsDto {

    String ad;
    String soyad;
    String laborantId;
    String rolename;
    int reportCount;

    public DetailsDto(Laborant laborant,int reportCount){
        this.ad= laborant.getAd();
        this.soyad=laborant.getSoyad();
        this.laborantId=laborant.getLaborantId();
        this.reportCount=reportCount;
        for (Role role1: laborant.getRoles()) {
            this.rolename=role1.getRoleName();
        }
    }

}
