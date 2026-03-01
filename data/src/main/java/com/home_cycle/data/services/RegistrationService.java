package com.home_cycle.data.services;

import com.home_cycle.data.components.DataMapping;
import com.home_cycle.data.dto.request.RegistrationDTO;
import com.home_cycle.data.dto.response.HouseholdResponseDTO;
import com.home_cycle.data.models.Household;
import com.home_cycle.data.models.User;
import com.home_cycle.data.repositories.HouseholdRepository;
import com.home_cycle.data.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {

    @Autowired private HouseholdRepository householdRepository;
    @Autowired private UserRepository userRepository;
    @Autowired private DataMapping dataMapping;

    @Transactional
    public HouseholdResponseDTO registerHousehold(RegistrationDTO dto) {
        Household household = new Household();
        household.setNotes(dto.getHouseholdNotes());
        household = householdRepository.save(household);

        User newUser = new User();
        newUser.setEmail(dto.getEmail());
        newUser.setPassword(dto.getPassword());
        newUser.setName(dto.getName());
        newUser.setHousehold(household);
        newUser.setPrimary(true);
        newUser = userRepository.save(newUser);

        household.setPrimaryUser(newUser);
        household.setCreatedBy(newUser);
        household.addUser(newUser);
        householdRepository.save(household);

        return dataMapping.toHouseholdDTO(household);
    }
}
