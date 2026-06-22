package com.eazybytes.jobportal.authConfig;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.password.CompromisedPasswordChecker;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eazybytes.jobportal.constant.ApplicationConstants;
import com.eazybytes.jobportal.dto.LoginRequestDTO;
import com.eazybytes.jobportal.dto.LoginResponseDTO;
import com.eazybytes.jobportal.dto.RegisterRequestDTO;
import com.eazybytes.jobportal.dto.UserDTO;
import com.eazybytes.jobportal.entity.JobPortalUser;
import com.eazybytes.jobportal.entity.Role;
import com.eazybytes.jobportal.repository.JobPortalUserRepository;
import com.eazybytes.jobportal.repository.RolesRepository;
import com.eazybytes.jobportal.security.util.JwtUtil;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;

    private final JwtUtil jwtUtil;

    private final PasswordEncoder passwordEncoder;

    private final JobPortalUserRepository userRepository;

    private final RolesRepository rolesRepository;

    private final CompromisedPasswordChecker compromisedPasswordChecker;

    @PostMapping(value = "/login/public", version="1.0")
    public ResponseEntity<LoginResponseDTO> apiLogin(@RequestBody LoginRequestDTO loginRequestDTO){
        try {             
        var resultAuthentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequestDTO.username(), loginRequestDTO.password()));
        // Logic to generate JWT token using the resultAuthentication details        
        String jwtToken = jwtUtil.generateToken(resultAuthentication);
        UserDTO userDTO = new UserDTO();
        var loginUser = (JobPortalUser) resultAuthentication.getPrincipal();
        BeanUtils.copyProperties(loginUser, userDTO);
        userDTO.setRole(loginUser.getRole().getName());
        userDTO.setUserId(loginUser.getId());
        LoginResponseDTO loginResponseDTO = new LoginResponseDTO(HttpStatus.OK.getReasonPhrase(),userDTO,jwtToken);
        return ResponseEntity.status(HttpStatus.OK).body(loginResponseDTO);
        } catch (BadCredentialsException e) {
            return buildErrorException(HttpStatus.UNAUTHORIZED, "Invalid username or password");
        } catch(AuthenticationException ae){
             return buildErrorException(HttpStatus.UNAUTHORIZED, "Authentication failed");
        } catch(Exception e){
             return buildErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "Unexpected error occured");
        }     
    }

    @PostMapping(value ="/register/public", version="1.0")
    public ResponseEntity<?> userRegisteration(@RequestBody RegisterRequestDTO registerRequestDTO){                      
        JobPortalUser user = new JobPortalUser();
        BeanUtils.copyProperties(registerRequestDTO, user); 
        user.setPasswordHash(passwordEncoder.encode(registerRequestDTO.passwordHash()));
        Role role = rolesRepository.findRoleByName(ApplicationConstants.ROLE_JOB_SEEKER).orElseThrow(() -> new IllegalArgumentException("Role not found : " + ApplicationConstants.ROLE_JOB_SEEKER));
        user.setRole(role);
        userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
    }

    private ResponseEntity<LoginResponseDTO> buildErrorException(HttpStatus status, String message){
        return ResponseEntity.status(status).body(new LoginResponseDTO(message, null, null));
    }

}
