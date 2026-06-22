package com.eazybytes.jobportal.audit;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import com.eazybytes.jobportal.util.ApplicationUtility;

@Component("auditorAwareImpl")
public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
       return Optional.of(ApplicationUtility.getLoggedInUserEmail());
    }    

}
