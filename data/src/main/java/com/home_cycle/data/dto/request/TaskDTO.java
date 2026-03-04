package com.home_cycle.data.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.Instant;
import java.time.LocalDate;

@Data
public class TaskDTO {
    @NotBlank(message = "Task Title is required")
    @Size(max = 100, message = "Task Title must be less than 100 characters")
    String title;
    @Size(min = 5, message = "Task Description must be at least 5 characters")
    private String description;
    private Integer householdId;
    @NotNull(message = "Due Date is required")
    private LocalDate dueDate; // TODO: Confirm correct data type for date
    private Boolean completed;
    private Instant completedAt; // TODO: Confirm correct data type for date
    private Integer recurrence; // TODO: Date type definition
    private Integer completedBy;
    private Integer createdBy;
    private Instant createdAt;

    public TaskDTO(String title, String description, Integer householdId, LocalDate dueDate, Boolean completed, Instant completedAt, Integer recurrence, Integer completedBy, Integer createdBy, Instant createdAt) {
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

    public Boolean isCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }
}
