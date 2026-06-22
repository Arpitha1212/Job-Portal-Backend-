package com.eazybytes.jobportal.scope;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import lombok.Getter;
import lombok.Setter;

@Component
@SessionScope
@Getter
@Setter
public class SessionScopedBean {

    private String name;

    public SessionScopedBean(){
        System.out.println("Session Scoped Bean");
    }
}
