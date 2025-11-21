package org.example.interviewmanager.dto;

import java.util.UUID;

public record OpenPositionDTO(UUID id, String level, String type, CompanyDTO company) { }
