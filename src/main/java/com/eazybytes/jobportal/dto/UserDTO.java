package com.eazybytes.jobportal.dto;

import java.time.Instant;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserDTO {

    private Long userId;
    private String name;
    private String email;
    private String mobileNumber;
    private String role;
    private String companyId;
    private String companyName;
    private Instant createdAt;

}
