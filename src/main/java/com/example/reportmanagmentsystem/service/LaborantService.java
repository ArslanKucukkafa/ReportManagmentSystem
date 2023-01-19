package com.example.reportmanagmentsystem.service;

import com.example.reportmanagmentsystem.config.security.JwtTokenUtil;
import com.example.reportmanagmentsystem.model.Laborant;
import com.example.reportmanagmentsystem.model.dto.LaborantDetailsDto;
import com.example.reportmanagmentsystem.model.dto.LaborantLoginDto;
import com.example.reportmanagmentsystem.model.dto.LaborantRegisterDto;
import com.example.reportmanagmentsystem.model.response.ErrorResponse;
import com.example.reportmanagmentsystem.model.response.LoginResponse;
import com.example.reportmanagmentsystem.model.response.Response;
import com.example.reportmanagmentsystem.model.response.LoginResponse;
import com.example.reportmanagmentsystem.repository.LaborantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LaborantService implements UserDetailsService {

private final LaborantRepository laborantRepository;
private final AuthenticationManager authenticationManager;
private final JwtTokenUtil jwtTokenUtil;

@Autowired
private PasswordEncoder encoder;
public void registerLaborant(LaborantRegisterDto registerDto){
    registerDto.setPassword(encoder.encode(registerDto.getPassword()));
    System.out.println(registerDto.getPassword());
    laborantRepository.save(registerDto.registerLaborantDto(registerDto));
    }

public Response loginLaborant(LaborantLoginDto loginDto){
   final Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getLaborant_id(),loginDto.getPassword()));
    // TODO WARNING
    // TODO Password encoded edilip control edilecek. Eksik  
    Optional<Laborant> isConfirmed = laborantRepository.findByLaborantId(loginDto.getLaborant_id());
    if(isConfirmed.isPresent()){
        return new ErrorResponse("Invalid password or laborant ıd",false);
    }
    else {
        String token = jwtTokenUtil.generateToken(authentication);
        return LoginResponse.builder().token(token).build();
    }
}

    public UserDetails loadUserByUsername(Long laborant_id) throws UsernameNotFoundException {
        Laborant laborant =laborantRepository.findByLaborantId(laborant_id).orElseThrow(()-> new UsernameNotFoundException("User not found with laborant_id: "+laborant_id));
        return new LaborantDetailsDto(laborant);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
