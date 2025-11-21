package org.example.interviewmanager.controller;

import jakarta.validation.Valid;
import org.example.interviewmanager.dto.OpenPositionDTO;
import org.example.interviewmanager.service.OpenPositionService;
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
@RequestMapping("/open-position")
public class OpenPositionController {

    private final OpenPositionService openPositionService;

    public OpenPositionController(OpenPositionService openPositionService) {
        this.openPositionService = openPositionService;
    }

    @PostMapping("/")
    public ResponseEntity<OpenPositionDTO> saveOpenPosition(@Valid @RequestBody OpenPositionDTO openPositionDTO) {
        OpenPositionDTO openPositionDTOSaved = openPositionService.saveOpenPosition(openPositionDTO);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(openPositionDTOSaved.id()) // Assuming MyEntity has an getId() method
                .toUri();
        return ResponseEntity.created(location).body(openPositionDTOSaved);
    }

    @GetMapping("/")
    public ResponseEntity<List<OpenPositionDTO>> fetchOpenPositions(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "level") String sortBy,
            @RequestParam(defaultValue = "true") boolean ascending
    ) {
        Sort sort = ascending ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return ResponseEntity.ok(openPositionService.getOpenPositions(pageable));
    }

    @GetMapping("/{level}")
    public ResponseEntity<List<OpenPositionDTO>> fetchOpenPositionsByLevel(
            @PathVariable String level,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "level") String sortBy,
            @RequestParam(defaultValue = "true") boolean ascending
    ) {
        Sort sort = ascending ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return ResponseEntity.ok(openPositionService.getOpenPositionsByLevel(level, pageable));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OpenPositionDTO> updateOpenPosition(@PathVariable UUID id, @Valid @RequestBody OpenPositionDTO openPositionDTO) {
        return ResponseEntity.ok(openPositionService.updateOpenPosition(openPositionDTO, id));
    }

}
