package com.example.reportmanagmentsystem.service;

import com.example.reportmanagmentsystem.model.Laborant;
import com.example.reportmanagmentsystem.model.dto.LaborantDetailsDto;
import com.example.reportmanagmentsystem.repository.LaborantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    LaborantRepository laborantRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Laborant laborant = laborantRepository.findByLaborantId(username).orElseThrow(()->new UsernameNotFoundException("User not found with laborant_id: \"+username"));
        System.out.println(laborant.getAd());
        return new LaborantDetailsDto(laborant);
    }

    /*    @Override
public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("loadByUsername not working");
        Laborant laborant =laborantRepository.findByLaborantId(username).orElseThrow(()-> new UsernameNotFoundException("User not found with laborant_id: "+username));
        System.out.println(laborant.getLaborantId());
        return new LaborantDetailsDto(laborant);
    }*/
}
