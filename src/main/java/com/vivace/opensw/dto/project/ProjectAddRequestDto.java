package com.vivace.opensw.dto.project;

import com.vivace.opensw.entity.*;

import java.time.LocalDate;

public record ProjectAddRequestDto(
        String title,
        String team_name,
        LocalDate deadline,
        String description,
        int iterationLen
) {
    public Project toEntity(ProjectAddRequestDto requestDto) {
        return Project.builder()
                .title(requestDto.title)
                .teamName(requestDto.team_name)
                .deadline(requestDto.deadline)
                .description(requestDto.description)
                .iterationLen(requestDto.iterationLen)
                .build();
    }
}
