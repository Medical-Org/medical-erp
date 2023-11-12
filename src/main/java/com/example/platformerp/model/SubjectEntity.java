package com.example.platformerp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "subject")
public class SubjectEntity extends BaseEntity {
    @Column(nullable = false)
    private String name;

    public SubjectEntity(UUID id, LocalDateTime created, LocalDateTime updated, String name) {
        super(id, created, updated);
        this.name = name;
    }
}
