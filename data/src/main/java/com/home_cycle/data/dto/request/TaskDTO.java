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
    @Size(min = 5, message = "Task Description must be at least 5 characters")
    private String description;
    private Integer householdId;
    @NotNull(message = "Due Date is required")
    private Long dueDate; // TODO: Confirm correct data type for date
    private Boolean completed;
    private Long completedAt; // TODO: Confirm correct data type for date
    private Integer recurrence; // TODO: Date type definition
    private Integer completedBy;
    private Integer createdBy;
    private Long createdAt;

    public TaskDTO(String title, String description, Integer householdId, Long dueDate, Boolean completed, Long completedAt, Integer recurrence, Integer completedBy, Integer createdBy, Long createdAt) {
        this.title = title;
        this.description = description;
        this.householdId = householdId;
        this.dueDate = dueDate;
        this.completed = completed;
        this.completedAt = completedAt;
        this.recurrence = recurrence;
        this.completedBy = completedBy;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
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

    public Integer getHouseholdId() {
        return householdId;
    }

    public void setHouseholdId(Integer householdId) {
        this.householdId = householdId;
    }

    public Long getDueDate() {
        return dueDate;
    }

    public void setDueDate(Long dueDate) {
        this.dueDate = dueDate;
    }

    public Boolean isCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public Long getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(Long completedAt) {
        this.completedAt = completedAt;
    }

    public Integer getRecurrence() {
        return recurrence;
    }

    public void setRecurrence(Integer recurrence) {
        this.recurrence = recurrence;
    }

    public Integer getCompletedBy() {
        return completedBy;
    }

    public void setCompletedBy(Integer completedBy) {
        this.completedBy = completedBy;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }
}
