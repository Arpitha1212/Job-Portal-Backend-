package com.eazybytes.jobportal.security;

import java.util.List;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.eazybytes.jobportal.entity.JobPortalUser;
import com.eazybytes.jobportal.repository.JobPortalUserRepository;

import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JobPortalUsernamePwdAuthenticationProvider  implements AuthenticationProvider {

    private final JobPortalUserRepository jobPotralUserRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public @Nullable Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        JobPortalUser user = jobPotralUserRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
        List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(user.getRole().getName()));
        if (passwordEncoder.matches(password, user.getPasswordHash())) {
            return UsernamePasswordAuthenticationToken.authenticated(user, null, authorities);
        }else{
            throw new RuntimeException("Invalid credentials");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        // Specify the type of authentication this provider supports
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
