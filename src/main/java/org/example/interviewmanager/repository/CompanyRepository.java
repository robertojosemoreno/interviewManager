package org.example.interviewmanager.repository;

import org.example.interviewmanager.repository.entity.Company;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CompanyRepository extends JpaRepository<Company, UUID> {
    Page<Company> findCompaniesByName(String name, Pageable pageable);
}
