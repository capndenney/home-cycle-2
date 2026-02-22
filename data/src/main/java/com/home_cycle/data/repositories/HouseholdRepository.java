package com.home_cycle.data.repositories;

import com.home_cycle.data.models.Household;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HouseholdRepository extends JpaRepository<Household, Integer> {
}
