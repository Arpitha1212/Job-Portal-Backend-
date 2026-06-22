package com.eazybytes.jobportal.aspect;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.authentication.password.CompromisedPasswordChecker;
import org.springframework.security.authentication.password.CompromisedPasswordDecision;
import org.springframework.stereotype.Component;

import com.eazybytes.jobportal.dto.RegisterRequestDTO;
import com.eazybytes.jobportal.entity.JobPortalUser;
import com.eazybytes.jobportal.exception.RegistrationValidationException;
import com.eazybytes.jobportal.repository.JobPortalUserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@RequiredArgsConstructor
@Slf4j
public class RegisterValidationAspect {

    private final CompromisedPasswordChecker compromisedPasswordChecker;
    private final JobPortalUserRepository jobPortalUserRepository;

    @Before("""
        execution(* com.eazybytes.jobportal.authConfig.AuthController
        .registerUser(..))
        """)
    public void validateBeforeRegister(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        RegisterRequestDTO request = (RegisterRequestDTO) args[0];
        log.info("🔍 Validating user registration request");
        Map<String, String> errors = new HashMap<>();
        // 1️⃣ Compromised password check
        CompromisedPasswordDecision decision =
                compromisedPasswordChecker.check(request.passwordHash());
        if (decision.isCompromised()) {
            errors.put("password", "Choose a strong password");
        }
        // 2️⃣ Existing user check
        Optional<JobPortalUser> existingUser =
                jobPortalUserRepository.findByEmailAndMobileNumber(
                        request.email(), request.mobileNumber());

        if (existingUser.isPresent()) {
            JobPortalUser user = existingUser.get();

            if (user.getEmail().equalsIgnoreCase(request.email())) {
                errors.put("email", "Email is already registered");
            }

            if (user.getMobileNumber().equals(request.mobileNumber())) {
                errors.put("mobileNumber", "Mobile number is already registered");
            }
        }

        // 3️⃣ Stop execution if validation fails
        if (!errors.isEmpty()) {
            log.warn("❌ Registration validation failed: {}", errors);
            throw new RegistrationValidationException(errors);
        }

        log.info("✅ Registration validation passed");
    }

}
