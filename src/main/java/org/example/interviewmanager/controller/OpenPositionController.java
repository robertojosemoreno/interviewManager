package org.example.interviewmanager.controller;

import jakarta.validation.Valid;
import org.example.interviewmanager.dto.OpenPositionDTO;
import org.example.interviewmanager.service.OpenPositionService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/open-position")
public class OpenPositionController {

    private final OpenPositionService openPositionService;

    public OpenPositionController(OpenPositionService openPositionService) {
        this.openPositionService = openPositionService;
    }

    @PostMapping("/")
    public OpenPositionDTO saveOpenPosition(@Valid @RequestBody OpenPositionDTO openPositionDTO) {
        return openPositionService.saveOpenPosition(openPositionDTO);
    }

    @GetMapping("/")
    public List<OpenPositionDTO> fetchOpenPositions(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "level") String sortBy,
            @RequestParam(defaultValue = "true") boolean ascending
    ) {
        Sort sort = ascending ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return openPositionService.getOpenPositions(pageable);
    }

    @GetMapping("/{level}")
    public List<OpenPositionDTO> fetchOpenPositionsByLevel(
            @PathVariable String level,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "level") String sortBy,
            @RequestParam(defaultValue = "true") boolean ascending
    ) {
        Sort sort = ascending ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return openPositionService.getOpenPositionsByLevel(level, pageable);
    }

    @PutMapping("/{id}")
    public OpenPositionDTO updateOpenPosition(@PathVariable UUID id, @Valid @RequestBody OpenPositionDTO openPositionDTO) {
        return openPositionService.updateOpenPosition(openPositionDTO, id);
    }

}
