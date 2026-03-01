package com.home_cycle.data.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HouseholdSummaryDTO {
    private Integer id;
    private String notes;
    private int memberCount;
    private boolean userIsPrimary;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public int getMemberCount() {
        return memberCount;
    }

    public void setMemberCount(int memberCount) {
        this.memberCount = memberCount;
    }

    public boolean isUserIsPrimary() {
        return userIsPrimary;
    }

    public void setUserIsPrimary(boolean userIsPrimary) {
        this.userIsPrimary = userIsPrimary;
    }
}
