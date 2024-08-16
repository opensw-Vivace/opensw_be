package com.vivace.opensw.dto.project;

import com.vivace.opensw.entity.*;

import java.time.LocalDate;
import java.util.List;

public record ProjectAddRequestDto(
        String title,
        String team_name,
        LocalDate deadline,
        String description,
        int iterationLen,
        List<String> positions
) {
    public Project toEntity() {
        return Project.builder()
                .title(this.title)
                .teamName(this.team_name)
                .deadline(this.deadline)
                .description(this.description)
                .iterationLen(this.iterationLen)

                .build();
    }
}
