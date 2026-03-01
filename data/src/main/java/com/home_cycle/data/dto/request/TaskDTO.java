package com.home_cycle.data.dto.request;

import com.home_cycle.data.models.Household;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class TaskDTO {
    @NotBlank(message = "Task Title is required")
    @Size(max = 100, message = "Task Title must be less than 100 characters")
    String title;
    @Min(value = 5, message = "Task Description must be at least 5 characters")
    private String description;
    private Household household;
    @NotNull(message = "Due Date is required")
    private long dueDate; // TODO: Confirm correct data type for date
    private boolean completed;
    private long completedAt; // TODO: Confirm correct data type for date
    private int recurrence; // TODO: Date type definition
    private int completedBy;
    private int createdBy;

    public TaskDTO(String title, String description, Household household, long dueDate, boolean completed, long completedAt, int recurrence, int completedBy, int createdBy) {
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
