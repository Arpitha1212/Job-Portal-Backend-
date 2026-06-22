package com.eazybytes.jobportal.company.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eazybytes.jobportal.company.service.CompanyService;
import com.eazybytes.jobportal.constant.ApplicationConstants;
import com.eazybytes.jobportal.dto.CompanyDTO;
import com.eazybytes.jobportal.dto.JobDTO;
import com.eazybytes.jobportal.entity.Company;
import com.eazybytes.jobportal.entity.Job;
import com.eazybytes.jobportal.repository.CompanyRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;

    @Override
    public List<CompanyDTO> getAllCompanyDetails() {
        List<Company> companyList =companyRepository.findAllWithJobByStatus(ApplicationConstants.JOB_STATUS_ACTIVE);
        return companyList.stream().map(this::transformCompanyToDto).collect(Collectors.toList());
    }

    private CompanyDTO transformCompanyToDto(Company company) {
        List<JobDTO> jobDtos = company.getJobs().stream()
                .map(this::transformJobToDto)
                .collect(Collectors.toList());
        return new CompanyDTO(  Long.valueOf(company.getId()), company.getName(), company.getLogo(),
                company.getIndustry(), company.getSize(), company.getRating(),
                company.getLocations(), company.getFounded(), company.getDescription(),
                company.getEmployees(), company.getWebsite(), company.getCreatedAt(),jobDtos);
    }

    private JobDTO transformJobToDto(Job job) {
        return new JobDTO(
                job.getId(),
                job.getTitle(),
                Long.valueOf(job.getCompany().getId()),
                job.getCompany().getName(),
                job.getCompany().getLogo(),
                job.getLocation(),
                job.getWorkType(),
                job.getJobType(),
                job.getCategory(),
                job.getExperienceLevel(),
                job.getSalaryMin(),
                job.getSalaryMax(),
                job.getSalaryCurrency(),
                job.getSalaryPeriod(),
                job.getDescription(),
                job.getRequirements(),
                job.getBenefits(),
                job.getPostedDate(),
                job.getApplicationDeadline(),
                job.getApplicationsCount(),
                job.getFeatured(),
                job.getUrgent(),
                job.getRemote(),
                job.getStatus()
        );
    }
}
