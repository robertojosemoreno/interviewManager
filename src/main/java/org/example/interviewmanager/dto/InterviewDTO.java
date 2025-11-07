package org.example.interviewmanager.dto;

import org.example.interviewmanager.repository.entity.Company;
import org.example.interviewmanager.repository.entity.OpenPosition;

import java.sql.Timestamp;
import java.util.UUID;

public class InterviewDTO {
    private UUID id;
    private String type;
    private Timestamp interviewDate;
    private OpenPosition position;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Timestamp getInterviewDate() {
        return interviewDate;
    }

    public void setInterviewDate(Timestamp interviewDate) {
        this.interviewDate = interviewDate;
    }

    public OpenPosition getPosition() {
        return position;
    }

    public void setPosition(OpenPosition position) {
        this.position = position;
    }
}
