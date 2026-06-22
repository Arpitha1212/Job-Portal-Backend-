package com.eazybytes.jobportal.security;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PathConfig {

    @Bean(name="publicPaths")
    public List<String> publicPaths(){

           return List.of(
            "/api/companies/public",
            "/api/auth/login/public",
             "/api/auth/register/public",            
             "/api/csrf-token/public",
            "/api/contacts/public",
            "/swagger-ui.html",
            "/swagger-ui/**",
            "/v3/api-docs/**",
            "/swagger-resources/**",
            "/webjars/**"
    );
    }

    @Bean(name="securedPath")
    public List<String> securedPaths(){
        return List.of("/api/**");
    }

    @Bean(name="adminPaths")
    public List<String> adminPaths() {
        return List.of(
            "/api/contacts/admin",
             "/api/contacts/page/admin",
             "/api/contacts/{id}/status/admin"
        );
    }
}
