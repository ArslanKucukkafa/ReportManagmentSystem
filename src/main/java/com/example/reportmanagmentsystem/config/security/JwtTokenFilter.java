package com.example.reportmanagmentsystem.config.security;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtTokenFilter  extends OncePerRequestFilter {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException, SignatureException {
        final String requestTokenHeader = request.getHeader("Authorization");

        String laborant_id = null;
        String jwtToken = null;

        if(requestTokenHeader!=null&& requestTokenHeader.startsWith("Bearer")){
            jwtToken = requestTokenHeader.substring(7);
            try {
                laborant_id = jwtTokenUtil.getUsernameFromToken(jwtToken);
            } catch (IllegalArgumentException e) {
                System.out.println("Unable to get JWT Token!");
            } catch (ExpiredJwtException e) {
                System.out.println("JWT Token has expired!");
            }
        }else{
            logger.warn("Token Must be start With Bearer. JWT Token does not begin with Bearer String");
        }
        System.out.println(laborant_id+" "+jwtToken+"------");
        if (laborant_id != null && jwtToken!=null && SecurityContextHolder.getContext().getAuthentication() == null){
            System.out.println("USER DETAİLDS SERVİCE İS WORK İN TOKEN FİLTER"+" İÇERDDDEDEEDEDEDE");
            UserDetails userDetails = userDetailsService.loadUserByUsername(laborant_id);
            if (jwtTokenUtil.tokenValidate(jwtToken)) {
                System.out.println(jwtToken);
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null,
                        userDetails.getAuthorities());
                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        else{
            System.out.println("else working security");
        }
        filterChain.doFilter(request, response);
    }
}
