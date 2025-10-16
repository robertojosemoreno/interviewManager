package org.example.interviewmanager.service;

import org.example.interviewmanager.dto.CompanyDTO;
import org.example.interviewmanager.repository.CompanyRepository;
import org.example.interviewmanager.repository.entity.Company;
import org.example.interviewmanager.utils.exception.CompanyNotFoundException;
import org.modelmapper.ModelMapper;
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
    private final ModelMapper modelMapper;

    public CompanyService(CompanyRepository companyRepository, ModelMapper modelMapper) {
        this.companyRepository = companyRepository;
        this.modelMapper = modelMapper;
    }

    public CompanyDTO getCompanyById(UUID id) {
        return modelMapper.map(companyRepository.findById(id), CompanyDTO.class);
    }

    public List<CompanyDTO> getCompaniesByName(String name, Pageable pageable) {
        Page<Company> result = companyRepository.findCompaniesByName(name, pageable);
        return result.stream().map((element) -> modelMapper.map(element, CompanyDTO.class)).collect(Collectors.toList());
    }

    public List<CompanyDTO> getCompanies(Pageable pageable) {
        Page<Company> result = companyRepository.findAll(pageable);
        return result.stream().map((element) -> modelMapper.map(element, CompanyDTO.class)).collect(Collectors.toList());
    }

    public CompanyDTO saveCompany(CompanyDTO companyDTO) {
        Company company = modelMapper.map(companyDTO, Company.class);
        return modelMapper.map(companyRepository.save(company), CompanyDTO.class);
    }

    public CompanyDTO updateCompany(CompanyDTO companyDTO, UUID id) {
        Company company = modelMapper.map(companyDTO, Company.class);
        Optional<Company> companyDbOptional = companyRepository.findById(id);
        if (companyDbOptional.isPresent()) {
            Company companyDb = companyDbOptional.get();
            if(Objects.nonNull(company.getName()) &&
                    !company.getName().isBlank() &&
                    !companyDb.getName().equals(company.getName())
            ) {
                companyDb.setName(company.getName());
            }
            return modelMapper.map(companyRepository.save(companyDb), CompanyDTO.class);
        } else {
            throw new CompanyNotFoundException("Company not found");
        }
    }

    public void deleteCompany(UUID id) {
        companyRepository.deleteById(id);
    }


}
