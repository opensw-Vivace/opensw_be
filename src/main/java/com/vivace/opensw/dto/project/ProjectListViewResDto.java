package com.vivace.opensw.dto.project;

import com.vivace.opensw.entity.Project;
import com.vivace.opensw.model.DocsStatus;
import com.vivace.opensw.model.ProjectStatus;

import java.time.LocalDate;

public record ProjectListViewResDto(
        Long project_id,
        String title,
        String description,
        LocalDate deadline,
        ProjectStatus status,
        String team_name
) {
    public static ProjectListViewResDto from(Project project) {
        return new ProjectListViewResDto(
                project.getId(),
                project.getTitle(),
                project.getDescription(),
                project.getDeadline(),
                project.getStatus(),
                project.getTeamName()

        );
    }
}
