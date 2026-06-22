package com.eazybytes.jobportal.company.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eazybytes.jobportal.company.service.CompanyService;
import com.eazybytes.jobportal.dto.CompanyDTO;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/companies")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;

    @GetMapping("/public")
    public ResponseEntity<List<CompanyDTO>> getAllCompanies(){
        List<CompanyDTO> companyDetails = companyService.getAllCompanyDetails();
        return ResponseEntity.ok().body(companyDetails);
    }
}
