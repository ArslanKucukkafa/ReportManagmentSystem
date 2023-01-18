package com.example.reportmanagmentsystem.model.dto;

import com.example.reportmanagmentsystem.model.Laborant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LaborantRegisterDto {

    private String ad;
    private String soyad;
    private Long laborant_id;
    private String password;

    public Laborant registerLaborantDto(LaborantRegisterDto laborantregisterDto){
        Laborant laborant = new Laborant();
        laborant.setAd(laborantregisterDto.getAd());
        laborant.setSoyad(laborantregisterDto.getSoyad());
        laborant.setLaborantId(laborantregisterDto.getLaborant_id());
        laborant.setPassword(laborantregisterDto.getPassword());
        return laborant;
    }
}
