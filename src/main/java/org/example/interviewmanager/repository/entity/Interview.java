package org.example.interviewmanager.repository.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(name = "interview")
public class Interview {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name="type", nullable = false)
    private String type;

    @Column(name="interview_date", nullable = false)
    private Timestamp interviewDate;

    @ManyToOne()
    @JoinColumn(name="position_id")
    private OpenPosition position;

    @ManyToOne()
    @JoinColumn(name="company_id")
    private Company company;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getType() { return type; }

    public void setType(String type) { this.type = type; }

    public Timestamp getInterviewDate() { return interviewDate; }

    public void setInterviewDate(Timestamp interviewDate) { this.interviewDate = interviewDate; }

    public OpenPosition getPosition() { return position; }

    public void setPosition(OpenPosition position) { this.position = position; }

    public Company getCompany() { return company; }

    public void setCompany(Company company) { this.company = company; }
}