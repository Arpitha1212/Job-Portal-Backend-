package com.eazybytes.jobportal.scope;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import lombok.Getter;
import lombok.Setter;

@Component
@RequestScope
@Getter
@Setter
public class RequestScopedBean {

    private String name;

    public RequestScopedBean(){
        System.out.println("Request Scoped Bean created. ");
    }

}
