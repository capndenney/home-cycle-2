package com.home_cycle.data.repositories;

import com.home_cycle.data.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Integer> {
}
