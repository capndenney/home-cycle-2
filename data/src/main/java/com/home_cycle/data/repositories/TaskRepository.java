package com.home_cycle.data.repositories;

import com.home_cycle.data.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Integer> {
    List<Task> findByHouseholdIdOrCreatorId(Integer householdId, Integer creatorId);
}
