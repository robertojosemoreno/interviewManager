package org.example.interviewmanager.dto;

import java.sql.Timestamp;
import java.util.UUID;

public record InterviewDTO(UUID id, String type, Timestamp interviewDate, OpenPositionDTO position) { }
