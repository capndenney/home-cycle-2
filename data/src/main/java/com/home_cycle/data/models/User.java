package com.home_cycle.data.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name="householdId", nullable = false)
    @JsonBackReference
    private Household household;
    private long createdAt; // TODO: Confirm correct data type for date
    @Column(unique = true)
    private String email;
    private String password; // TODO: Password encryption or hashing?
    private boolean isPrimary;

    public User() {
    }

    public User(int id, Household household, long createdAt, String email, String password, boolean isPrimary) {
        this.id = id;
        this.household = household;
        this.createdAt = createdAt;
        this.email = email;
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

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
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
