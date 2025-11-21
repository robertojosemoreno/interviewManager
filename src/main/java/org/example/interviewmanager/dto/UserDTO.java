package org.example.interviewmanager.dto;

import java.util.Date;
import java.util.UUID;

public record UserDTO(UUID id, String fullName, String email, String password, Date createdAt, Date updatedAt) {
    public String getUsername() {
        return email;
    }
}
