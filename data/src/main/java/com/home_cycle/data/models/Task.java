package com.home_cycle.data.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

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
    private long dueDate; // TODO: Confirm correct data type for date
    private boolean completed;
    @UpdateTimestamp
    private Timestamp completedAt; // TODO: Confirm correct data type for date
    private int recurrence; // TODO: Date type definition
    @ManyToOne
    @JoinColumn(name="completed_by")
    private User completedBy;
    @ManyToOne
    @JoinColumn(name="created_by", nullable = false)
    private User createdBy;
    @Column(updatable = false)
    @CreationTimestamp
    private Timestamp createdAt;

}
