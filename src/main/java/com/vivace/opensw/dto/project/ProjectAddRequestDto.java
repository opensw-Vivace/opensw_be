package com.vivace.opensw.dto.project;

import com.vivace.opensw.entity.Participate;
import com.vivace.opensw.entity.Project;
import com.vivace.opensw.entity.Position;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class ProjectAddRequestDto {
    private String title;
    private String teamName;
    private LocalDate deadline;
    private String description;
    private int iterationLen;
    private Long memberId;  // 프로젝트를 생성한 사용자의 ID
    private List<String> positionName; // 사용자의 역할 (포지션 이름)

    public Project toEntity() {
        return Project.builder()
            .title(this.title)
            .teamName(this.teamName)
            .deadline(this.deadline)
            .description(this.description)
            .iterationLen(this.iterationLen)
            .build();
    }
}