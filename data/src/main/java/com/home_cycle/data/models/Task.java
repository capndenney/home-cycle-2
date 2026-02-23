package com.home_cycle.data.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
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
    private long completedAt; // TODO: Confirm correct data type for date
    private int recurrence; // TODO: Date type definition
    private int completedBy;
    private int createdBy;

    public Task() {
    }

    public Task(int id, String title, String description, Household household, long dueDate, boolean completed, long completedAt, int recurrence, int completedBy, int createdBy) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.household = household;
        this.dueDate = dueDate;
        this.completed = completed;
        this.completedAt = completedAt;
        this.recurrence = recurrence;
        this.completedBy = completedBy;
        this.createdBy = createdBy;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Household getHousehold() {
        return household;
    }

    public void setHousehold(Household household) {
        this.household = household;
    }

    public long getDueDate() {
        return dueDate;
    }

    public void setDueDate(long dueDate) {
        this.dueDate = dueDate;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public long getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(long completedAt) {
        this.completedAt = completedAt;
    }

    public int getRecurrence() {
        return recurrence;
    }

    public void setRecurrence(int recurrence) {
        this.recurrence = recurrence;
    }

    public int getCompletedBy() {
        return completedBy;
    }

    public void setCompletedBy(int completedBy) {
        this.completedBy = completedBy;
    }

    public int getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }
}
