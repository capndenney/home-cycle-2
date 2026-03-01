package com.home_cycle.data.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HouseholdResponseDTO {
    private Integer id;
    private String notes;
    private UserResponseDTO primaryUser;
    private List<UserResponseDTO> members;
    private List<TaskResponseDTO> tasks;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public UserResponseDTO getPrimaryUser() {
        return primaryUser;
    }

    public void setPrimaryUser(UserResponseDTO primaryUser) {
        this.primaryUser = primaryUser;
    }

    public List<UserResponseDTO> getMembers() {
        return members;
    }

    public void setMembers(List<UserResponseDTO> members) {
        this.members = members;
    }

    public List<TaskResponseDTO> getTasks() {
        return tasks;
    }

    public void setTasks(List<TaskResponseDTO> tasks) {
        this.tasks = tasks;
    }
}
