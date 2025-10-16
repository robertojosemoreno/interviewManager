package org.example.interviewmanager.repository.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "open_position")
public class OpenPosition {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name="level", nullable = false)
    private String level;

    @Column(name="type", nullable = false)
    private String type;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getLevel() { return level; }

    public void setLevel(String level) { this.level = level; }

    public String getType() { return type; }

    public void setType(String type) { this.type = type; }

}