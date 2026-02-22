package com.home_cycle.data.models;

public class Household {
    private int id;
    private int createdBy;
    private long createdAt; // TODO: Confirm correct data type for date
    private String notes;

    public Household() {
    }

    public Household(int id, int createdBy, long createdAt, String notes) {
        this.id = id;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
        this.notes = notes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
