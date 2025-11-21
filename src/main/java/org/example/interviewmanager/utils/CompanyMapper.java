package org.example.interviewmanager.utils;

import org.example.interviewmanager.dto.CompanyDTO;
import org.example.interviewmanager.repository.entity.Company;

public class CompanyMapper {
    public static CompanyDTO toDTO(Company company) {
        return new CompanyDTO(company.getId(), company.getName());
    }

    public static Company toEntity(CompanyDTO companyDTO) {
        Company company = new Company();
        company.setId(companyDTO.id());
        company.setName(companyDTO.name());
        return company;
    }
}
