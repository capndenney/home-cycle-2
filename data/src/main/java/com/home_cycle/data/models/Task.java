package com.home_cycle.data.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String description;
    @ManyToOne
    @JoinColumn(name="household", nullable = false)
    @JsonBackReference
    private Household household;
    private LocalDate dueDate;
    private boolean completed;
    @UpdateTimestamp
    private Instant completedAt;
    private int recurrence; // For my date math, I am simply using "plusDays", so int will suffice.
    @ManyToOne
    @JoinColumn(name="completed_by")
    private User completedBy;
    @ManyToOne
    @JoinColumn(name="created_by", nullable = false)
    private User createdBy;
    @Column(updatable = false)
    @CreationTimestamp
    private Instant createdAt;

}
