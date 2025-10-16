package org.example.interviewmanager.controller;

import jakarta.validation.Valid;
import org.example.interviewmanager.dto.CompanyDTO;
import org.example.interviewmanager.service.CompanyService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/company")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping("/")
    public CompanyDTO saveCompany(@Valid @RequestBody CompanyDTO companyDTO) {
        return companyService.saveCompany(companyDTO);
    }

    @GetMapping("/")
    public List<CompanyDTO> fetchCompanies(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "true") boolean ascending
    ) {
        Sort sort = ascending ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return companyService.getCompanies(pageable);
    }

    @GetMapping("/{name}")
    public List<CompanyDTO> fetchCompaniesByName(
            @PathVariable String name,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "true") boolean ascending
    ) {
        Sort sort = ascending ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return companyService.getCompaniesByName(name,pageable);
    }

    @PutMapping("/{id}")
    public CompanyDTO updateCompany(@PathVariable UUID id, @Valid @RequestBody  CompanyDTO companyDTO) {
        return companyService.updateCompany(companyDTO, id);
    }

}
