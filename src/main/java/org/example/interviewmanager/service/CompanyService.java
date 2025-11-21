package org.example.interviewmanager.service;

import org.example.interviewmanager.dto.CompanyDTO;
import org.example.interviewmanager.repository.CompanyRepository;
import org.example.interviewmanager.repository.entity.Company;
import org.example.interviewmanager.utils.CompanyMapper;
import org.example.interviewmanager.utils.exception.CompanyNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public CompanyDTO getCompanyById(UUID id) {
        return CompanyMapper.toDTO(
                companyRepository.findById(id)
                        .orElseThrow(() -> new CompanyNotFoundException("Company not found")));
    }

    public List<CompanyDTO> getCompaniesByName(String name, Pageable pageable) {
        Page<Company> result = companyRepository.findCompaniesByName(name, pageable);
        return result.stream().map(CompanyMapper::toDTO).collect(Collectors.toList());
    }

    public List<CompanyDTO> getCompanies(Pageable pageable) {
        Page<Company> result = companyRepository.findAll(pageable);
        return result.stream().map(CompanyMapper::toDTO).collect(Collectors.toList());
    }

    public CompanyDTO saveCompany(CompanyDTO companyDTO) {
        Company company = CompanyMapper.toEntity(companyDTO);
        return CompanyMapper.toDTO(companyRepository.save(company));
    }

    public CompanyDTO updateCompany(CompanyDTO companyDTO, UUID id) {
        Company company = CompanyMapper.toEntity(companyDTO);
        Optional<Company> companyDbOptional = companyRepository.findById(id);
        if (companyDbOptional.isPresent()) {
            Company companyDb = companyDbOptional.get();
            if(Objects.nonNull(company.getName()) &&
                    !company.getName().isBlank() &&
                    !companyDb.getName().equals(company.getName())
            ) {
                companyDb.setName(company.getName());
            }
            return CompanyMapper.toDTO(companyRepository.save(companyDb));
        } else {
            throw new CompanyNotFoundException("Company not found");
        }
    }

    public void deleteCompany(UUID id) {
        companyRepository.deleteById(id);
    }


}
