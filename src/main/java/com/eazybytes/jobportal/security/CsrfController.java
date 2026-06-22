package com.eazybytes.jobportal.security;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/csrf-token")
public class CsrfController {

    // This endpoint will return the CSRF token to the client. 
    // The client can then include this token in the headers of subsequent requests
    //  to protected endpoints.
    @GetMapping(value="/public",version="1.0")
    public CsrfToken getCsrfToken(HttpServletRequest request) {
        return (CsrfToken) request.getAttribute(CsrfToken.class.getName());
    }
    
}
