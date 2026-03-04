package com.home_cycle.data.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "household")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Household {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToOne
    @JoinColumn(name = "creator_id", nullable = true)
    private User createdBy;
    @Column(updatable = false)
    @CreationTimestamp
    private Instant createdAt;
    @OneToMany(mappedBy = "household", cascade = CascadeType.ALL)
    private List<User> users = new ArrayList<>();
    @OneToOne
    @JoinColumn(name = "primary_user_id", nullable = true)
    private User primaryUser;
    private String notes;
    @OneToMany(mappedBy = "household", cascade = CascadeType.ALL)
    private List<Task> tasks = new ArrayList<>();

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
