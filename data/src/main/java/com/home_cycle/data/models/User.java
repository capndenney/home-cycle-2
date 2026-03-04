package com.home_cycle.data.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

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
    private Timestamp createdAt; // TODO: Confirm correct data type for date
    @Column(unique = true)
    private String email;
    private String password; // TODO: Password encryption or hashing?
    private boolean isPrimary;

    public User() {
    }

    public User(int id, Household household, String name, Timestamp createdAt, String email, String password, boolean isPrimary) {
        this.id = id;
        this.household = household;
        this.createdAt = createdAt;
        this.email = email;
        this.name = name;
        this.password = password;
        this.isPrimary = isPrimary;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Household getHousehold() {
        return household;
    }

    public void setHousehold(Household household) {
        this.household = household;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isPrimary() {
        return isPrimary;
    }

    public void setPrimary(boolean primary) {
        isPrimary = primary;
    }
}
