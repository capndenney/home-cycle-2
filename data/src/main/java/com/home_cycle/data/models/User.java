package com.home_cycle.data.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.time.Instant;

@Data
@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name="household", nullable = true)
    @JsonBackReference
    private Household household;
    private String name;
    @Column(updatable = false)
    @CreationTimestamp
    private Instant createdAt;
    @Column(unique = true)
    private String email;
    private String password; // TODO: Password storage method?
    private boolean isPrimary;

    public User() {
    }

    public User(int id, Household household, String name, Instant createdAt, String email, String password, boolean isPrimary) {
        this.id = id;
        this.household = household;
        this.createdAt = createdAt;
        this.email = email;
        this.name = name;
        this.password = password;
        this.isPrimary = isPrimary;
    }
}
