package com.eazybytes.jobportal.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.eazybytes.jobportal.constant.ApplicationConstants;
import com.eazybytes.jobportal.entity.JobPortalUser;

public class ApplicationUtility {

    public static String getLoggedInUserEmail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null || !authentication.isAuthenticated() || authentication.getPrincipal().equals("anonymousUser")) {
            return ApplicationConstants.SYSTEM;
        }

        Object principal = authentication.getPrincipal();
        String email;
        if (principal instanceof JobPortalUser userDetails) {
            email = userDetails.getEmail();
        } else {
            return principal.toString();
        }   

        return email;
    }

}
