package com.example.reportmanagmentsystem.model.dto;

import com.example.reportmanagmentsystem.model.Laborant;
import com.example.reportmanagmentsystem.model.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

public class LaborantDetailsDto implements UserDetails {

    private Laborant laborant;

    public LaborantDetailsDto(Laborant laborant){
        this.laborant = laborant;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        System.out.println(" GrantedAuthority is working");
        Set<Role> roles = laborant.getRoles();
        List<SimpleGrantedAuthority> grantedAuthorities = new ArrayList<>();
        for (Role role : roles) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        }
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return laborant.getPassword();
    }

    @Override
    public String getUsername() {
        return laborant.getLaborantId();
    }

    @Override
    public boolean isAccountNonExpired() { return true;}

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return laborant.isEnabled();
    }
}
