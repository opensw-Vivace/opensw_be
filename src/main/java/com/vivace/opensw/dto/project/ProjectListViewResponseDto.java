package com.vivace.opensw.dto.project;

import com.vivace.opensw.entity.Member;
import com.vivace.opensw.entity.Project;

import java.time.LocalDate;

public record ProjectListViewResponseDto(
        Long id,
        String title,
        String description,
        LocalDate deadline
) {
    public static ProjectListViewResponseDto from(Project project) {
        return new ProjectListViewResponseDto(
                project.getId(),
                project.getTitle(),
                project.getDescription(),
                project.getDeadline()
        );
    }
}
