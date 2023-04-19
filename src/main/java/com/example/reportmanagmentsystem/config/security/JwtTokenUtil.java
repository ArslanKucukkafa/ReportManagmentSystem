package com.example.reportmanagmentsystem.config.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Date;

/*
 Burda 3 adet işlem yapılmaktadır
 1. işlem token yaratma
 2. işlem token token dan username alma
 3. işlem token geçerlilik süresi kontrolü
 */
@Service
public class JwtTokenUtil implements Serializable {


    private final String secret;
    private final long tokenValidty;

    public JwtTokenUtil(@Value("${app.jwt.secret}") String secret,@Value("${app.jwt.token.validity}")  long tokenValidty) {
        this.secret = secret;
        this.tokenValidty = tokenValidty;
    }

    public String generateToken(Authentication authentication){
        return Jwts.builder().setSubject(authentication.getName()).setExpiration(
                new Date(System.currentTimeMillis()+1000*tokenValidty)).
                setIssuer("Arslan19")
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .signWith(SignatureAlgorithm.HS256,secret).compact();
    }

public String getUsernameFromToken(String token){
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
}
public boolean tokenValidate(String token){
    Boolean exprationStatus = Jwts.parser().setSigningKey(secret).parseClaimsJws(token)
            .getBody().getExpiration().after(new Date(System.currentTimeMillis()));
    if (exprationStatus && getUsernameFromToken(token) != null) {
        return true;
    }
    return false;
    }
}
