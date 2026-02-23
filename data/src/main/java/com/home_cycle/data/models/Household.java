package com.home_cycle.data.models;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Household {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private int createdBy;
    private long createdAt; // TODO: Confirm correct data type for date
    @OneToMany(mappedBy = "household", cascade = CascadeType.ALL)
    private List<User> users;
    private String notes;
    @OneToMany(mappedBy = "household", cascade = CascadeType.ALL)
    private List<Task> tasks;

    public Household() {
    }

    public Household(int id, int createdBy, long createdAt, String notes) {
        this.id = id;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
        this.notes = notes;
    }

    public void addUser(User user) {
        users.add(user);
        user.setHousehold(this);
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
