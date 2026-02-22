package com.home_cycle.data.models;

public class User {
    private int id;
    private int householdId;
    private long createdAt; // TODO: Confirm correct data type for date
    private String email;
    private String password; // TODO: Password encryption or hashing?
    private boolean isPrimary;

    public User() {
    }

    public User(int id, int householdId, long createdAt, String email, String password, boolean isPrimary) {
        this.id = id;
        this.householdId = householdId;
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

    public int getHouseholdId() {
        return householdId;
    }

    public void setHouseholdId(int householdId) {
        this.householdId = householdId;
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
