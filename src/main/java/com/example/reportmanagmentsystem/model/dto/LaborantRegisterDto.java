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

    private String name;
    private String surname;
    private String laborant_id;
    private String password;

    public Laborant registerLaborantDto(LaborantRegisterDto laborantregisterDto){
        Laborant laborant = new Laborant();
        laborant.setAd(laborantregisterDto.getName());
        laborant.setSoyad(laborantregisterDto.getSurname());
        laborant.setLaborantId(laborantregisterDto.getLaborant_id());
        laborant.setPassword(laborantregisterDto.getPassword());
        return laborant;
    }
}
