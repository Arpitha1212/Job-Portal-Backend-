package com.eazybytes.jobportal.security.util;


import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

import javax.crypto.SecretKey;

import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import com.eazybytes.jobportal.entity.JobPortalUser;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtUtil {

    private final Environment env;

    public String generateToken(Authentication authentication) {

       String jwtToken;
       String secretKey = env.getProperty("JWT_SECRET", "jxgEQeXHuPq8VdbyYFNkANdudQ53YUn4");
       // Logic to generate JWT token using the secretKey and authentication details    
       SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
       JobPortalUser fetchedUserDetails = (JobPortalUser) authentication.getPrincipal();
       
       // Logic to generate JWT token using the key and fetchedUserDetails
      jwtToken = Jwts.builder()
        .issuer("Job Portal")
        .subject("JWT Token")
        .claim("name", fetchedUserDetails.getName())
        .claim("email", fetchedUserDetails.getEmail())
        .claim("mobileNumber", fetchedUserDetails.getMobileNumber())
        .claim(
                "roles",
                authentication.getAuthorities()
                        .stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.joining(","))
        )
        .issuedAt(new java.util.Date())
        .expiration(new java.util.Date(
                System.currentTimeMillis() + 86400000
        ))
        .signWith(key)
        .compact();

     return jwtToken;
    }
}
