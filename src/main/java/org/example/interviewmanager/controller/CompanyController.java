package org.example.interviewmanager.controller;

import jakarta.validation.Valid;
import org.example.interviewmanager.dto.CompanyDTO;
import org.example.interviewmanager.service.CompanyService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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
    public ResponseEntity<CompanyDTO> saveCompany(@Valid @RequestBody CompanyDTO companyDTO) {
        CompanyDTO companyDTOSaved = companyService.saveCompany(companyDTO);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(companyDTOSaved.getId()) // Assuming MyEntity has an getId() method
                .toUri();
        return ResponseEntity.created(location).body(companyDTOSaved);
    }

    @GetMapping("/")
    public ResponseEntity<List<CompanyDTO>> fetchCompanies(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "true") boolean ascending
    ) {
        Sort sort = ascending ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return ResponseEntity.ok(companyService.getCompanies(pageable));
    }

    @GetMapping("/{name}")
    public  ResponseEntity<List<CompanyDTO>> fetchCompaniesByName(
            @PathVariable String name,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "true") boolean ascending
    ) {
        Sort sort = ascending ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return ResponseEntity.ok(companyService.getCompaniesByName(name,pageable));
    }

    @PutMapping("/{id}")
    public  ResponseEntity<CompanyDTO> updateCompany(@PathVariable UUID id, @Valid @RequestBody  CompanyDTO companyDTO) {
        return  ResponseEntity.ok(companyService.updateCompany(companyDTO, id));
    }

}
