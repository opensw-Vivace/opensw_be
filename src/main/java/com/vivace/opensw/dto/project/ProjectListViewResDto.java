package com.vivace.opensw.dto.project;

import com.vivace.opensw.entity.Project;

import java.time.LocalDate;

public record ProjectListViewResDto(
        Long id,
        String title,
        String description,
        LocalDate deadline
) {
    public static ProjectListViewResDto from(Project project) {
        return new ProjectListViewResDto(
                project.getId(),
                project.getTitle(),
                project.getDescription(),
                project.getDeadline()
        );
    }
}
