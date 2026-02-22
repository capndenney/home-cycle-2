package com.home_cycle.data.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class TaskDTO {
    @NotBlank(message = "Task Title is required")
    @Size(max = 100, message = "Task Title must be less than 100 characters")
    String title;

    private String description;
    private int householdId;
    @NotNull(message = "Due Date is required")
    private long dueDate; // TODO: Confirm correct data type for date
    private boolean completed;
    private long completedAt; // TODO: Confirm correct data type for date
    private int recurrence; // TODO: Date type definition
    private int completedBy;
    private int createdBy;

    public TaskDTO(String title, String description, int householdId, long dueDate, boolean completed, long completedAt, int recurrence, int completedBy, int createdBy) {
        this.title = title;
        this.description = description;
        this.householdId = householdId; // TODO: Do we need this value, or can HHID be added on the back?
        this.dueDate = dueDate;
        this.completed = completed;
        this.completedAt = completedAt;
        this.recurrence = recurrence;
        this.completedBy = completedBy;
        this.createdBy = createdBy;
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

    public int getHouseholdId() {
        return householdId;
    }

    public void setHouseholdId(int householdId) {
        this.householdId = householdId;
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
