package com.example.reportmanagmentsystem.service;

import com.example.reportmanagmentsystem.model.Laborant;
import com.example.reportmanagmentsystem.model.dto.LaborantDetailsDto;
import com.example.reportmanagmentsystem.repository.LaborantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    LaborantRepository laborantRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Laborant laborant = laborantRepository.findByLaborantId(username).orElseThrow(()->new UsernameNotFoundException("User not found with laborant_id: \"+username"));
        return new LaborantDetailsDto(laborant);
    }
}
