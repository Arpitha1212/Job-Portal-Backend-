package com.eazybytes.jobportal.scope;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/scope")
@RequiredArgsConstructor
public class ScopeController {

    private final SessionScopedBean sessionScopedBean;

    private final RequestScopedBean requestScopedBean;

    
    //public 


}
