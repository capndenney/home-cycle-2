package com.home_cycle.data.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskResponseDTO {

    private Integer id;
    private String title;
    private String description;
    private boolean completed;
    private int recurrence;
}
