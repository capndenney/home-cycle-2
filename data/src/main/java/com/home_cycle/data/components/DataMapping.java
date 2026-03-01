package com.home_cycle.data.components;

import com.home_cycle.data.dto.response.HouseholdResponseDTO;
import com.home_cycle.data.dto.response.TaskResponseDTO;
import com.home_cycle.data.dto.response.UserResponseDTO;
import com.home_cycle.data.models.Household;
import com.home_cycle.data.models.Task;
import com.home_cycle.data.models.User;
import org.springframework.stereotype.Component;

@Component
public class DataMapping {

    public UserResponseDTO toUserDTO(User user) {
        return new UserResponseDTO(
            user.getId(),
            user.getEmail(),
            user.getName(),
            user.isPrimary()
        );
    }

    public TaskResponseDTO toTaskDTO(Task task) {
        return new TaskResponseDTO(
            task.getId(),
            task.getTitle(),
            task.getDescription(),
            task.isCompleted(),
            task.getRecurrence()
        );
    }

    public HouseholdResponseDTO toHouseholdDTO(Household household) {
        return new HouseholdResponseDTO(
            household.getId(),
            household.getNotes(),
            toUserDTO(household.getPrimaryUser()),
            household.getUsers().stream().map(this::toUserDTO).toList(),
            household.getTasks().stream().map(this::toTaskDTO).toList()
        );
    }
}
