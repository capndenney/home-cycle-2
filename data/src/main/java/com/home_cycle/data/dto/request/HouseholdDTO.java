package com.home_cycle.data.dto.request;

import com.home_cycle.data.models.Task;
import com.home_cycle.data.models.User;

import java.util.List;

public class HouseholdDTO {
    private String notes;
    // TODO: Do we want a list of users in request DTO?
    private List<User> users;
    // TODO: Do we want a list of tasks in request DTO?
    private List<Task> tasks;

    public HouseholdDTO(String notes, List<User> users, List<Task> tasks) {
        this.notes = notes;
        this.users = users;
        this.tasks = tasks;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}
