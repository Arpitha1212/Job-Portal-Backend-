package com.eazybytes.jobportal.security;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.password.CompromisedPasswordChecker;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.password.HaveIBeenPwnedRestApiPasswordChecker;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.eazybytes.jobportal.filter.JwtTokenValidatorFilter;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class JobPortalSecurityConfig {

    @Qualifier("securedPath")
    public final List<String> securedPath;

    @Qualifier("publicPaths")
    public final List<String> publicPaths;

    @Qualifier("adminPaths")
    public final List<String> adminPaths;


   @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http){
        // CSRF protection is enabled by default in Spring Security. However, for stateless REST APIs,
        //  it is common to disable CSRF protection since the API is not vulnerable to CSRF attacks
        // j. If you are building a web application that uses cookies for authentication, you should keep CSRF protection enabled.
        return http.csrf(csrf -> csrf.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                        .csrfTokenRequestHandler(new CsrfTokenRequestAttributeHandler()                          
                        )).
            cors(corsConfig -> corsConfig.configurationSource(corsConfigurationSource())).
            authorizeHttpRequests((requests)-> {
            publicPaths.forEach(path -> requests.requestMatchers(path).permitAll());
             //hasrole method will automatically prefix the role with "ROLE_", so "ADMIN" becomes "ROLE_ADMIN" in the background
            adminPaths.forEach(path->  requests.requestMatchers(path).hasRole("ADMIN"));
            securedPath.forEach(path->  requests.requestMatchers(path).authenticated());           
            requests.anyRequest().authenticated();
        }).addFilterBefore(
                    new JwtTokenValidatorFilter(publicPaths),
                    UsernamePasswordAuthenticationFilter.class
            ).formLogin((flc) -> flc.disable()).httpBasic(httpBasic -> httpBasic.disable())
            .exceptionHandling(exception -> exception.accessDeniedHandler((request, response, accessDeniedException) -> {
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                response.setContentType("application/json");
                response.getWriter().write("{\"error\": \"Access Denied\",\"message\": \"You don't have permission to access this resource\"}");
            })
           ).build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(Arrays.asList("http://localhost:5173"));
        config.setAllowedMethods(Collections.singletonList("*"));
        config.setAllowedHeaders(Collections.singletonList("*"));
        config.setAllowCredentials(true);
        config.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }
// added for in memory authentication, can be removed when we have db authentication in place
// @Bean
// public UserDetailsService userDetailsService() {
//   var user1 = User.builder()
//         .username("arpitha")
//         .password("$2a$10$TPwevBcOIb6J/DimietAyuCdds.CvRUPBl4y7Bv71KqiijMQvjPzW")
//         .roles("USER")
//         .build();

//     var admin1 = User.builder()
//             .username("admin")
//             .password("$2a$10$34mryp3m1So/.rEcbBTKROAyyVXCLRuW1yyBnYUzVLQQZBE2GEhkW")
//             .roles("ADMIN")
//             .build();

//     return new InMemoryUserDetailsManager(user1, admin1);
// }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationProvider authenticationProvider){
     return new ProviderManager(authenticationProvider);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();  
    }


    @Bean
    public CompromisedPasswordChecker compromisedPasswordHandler(){
        return new HaveIBeenPwnedRestApiPasswordChecker();
    }
}
