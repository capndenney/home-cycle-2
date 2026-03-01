package com.home_cycle.data.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Household {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToOne
    @JoinColumn(name = "creator_id", unique = true)
    private User createdBy;
    private long createdAt; // TODO: date type
    @OneToMany(mappedBy = "household", cascade = CascadeType.ALL)
    private List<User> users;
    private User primaryUser;
    private String notes;
    @OneToMany(mappedBy = "household", cascade = CascadeType.ALL)
    private List<Task> tasks;

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
}
