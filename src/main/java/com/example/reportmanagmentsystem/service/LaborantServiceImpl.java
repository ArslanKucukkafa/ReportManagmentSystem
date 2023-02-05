package com.example.reportmanagmentsystem.service;

import com.example.reportmanagmentsystem.config.security.JwtTokenUtil;
import com.example.reportmanagmentsystem.model.Laborant;
import com.example.reportmanagmentsystem.model.Role;
import com.example.reportmanagmentsystem.model.dto.LaborantDetailsDto;
import com.example.reportmanagmentsystem.model.dto.LaborantLoginDto;
import com.example.reportmanagmentsystem.model.dto.LaborantRegisterDto;
import com.example.reportmanagmentsystem.model.response.ErrorResponse;
import com.example.reportmanagmentsystem.model.response.LoginResponse;
import com.example.reportmanagmentsystem.model.response.Response;
import com.example.reportmanagmentsystem.repository.LaborantRepository;
import com.example.reportmanagmentsystem.repository.RoleRepository;
import com.example.reportmanagmentsystem.service.interfaces.LaborantService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LaborantServiceImpl implements  LaborantService {

private final Logger logger = LoggerFactory.getLogger(LaborantServiceImpl.class);
@Autowired
private final LaborantRepository laborantRepository;
private final RoleRepository roleRepository;
private final AuthenticationManager authenticationManager;
private final JwtTokenUtil jwtTokenUtil;


    @Autowired
    private PasswordEncoder encoder;
@Override
public void registerLaborant(LaborantRegisterDto registerDto){
    registerDto.setPassword(encoder.encode(registerDto.getPassword()));
    Laborant laborant = registerDto.registerLaborantDto(registerDto);
    Role role = roleRepository.findByRoleName("LABORANT");
    laborant.setRoles(new HashSet<>());
    laborant.getRoles().add(role);
    System.out.println(registerDto.getPassword());
    laborantRepository.save(laborant);
    }
@Override
public Response loginLaborant(LaborantLoginDto loginDto) throws AuthenticationException {

    System.out.println("Login laborant Service ");


    Authentication  authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getLaborant_id(),loginDto.getPassword()));

    System.out.println("Login laborant Service is ---");
    // TODO WARNING
    // TODO Password encoded edilip control edilecek. Eksik  
    Optional<Laborant> isConfirmed = laborantRepository.findByLaborantId(loginDto.getLaborant_id());
    System.out.println(isConfirmed.isPresent()+"  "+isConfirmed);
    if(isConfirmed.isPresent()){
        String token = jwtTokenUtil.generateToken(authentication);
        return LoginResponse.builder().token(token).build();
    }
    else {
        return new ErrorResponse("Invalid password or laborant ıd",false);}
    }
}
