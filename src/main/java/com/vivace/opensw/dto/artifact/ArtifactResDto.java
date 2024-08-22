package com.vivace.opensw.dto.artifact;

import com.vivace.opensw.entity.ArtifactCreator;
import com.vivace.opensw.entity.ArtifactType;
import com.vivace.opensw.entity.Img;
import com.vivace.opensw.entity.Project;
import com.vivace.opensw.model.ArtifactStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ArtifactResDto {

    private Long id;

    private String title;

    private String subtitle;

    private ArtifactStatus status;

    private LocalDate deadline;

    private Long projectId;

    private Long artifactTypeId;

    private List<String> imgPathList;

    private List<Long> artifactCreatorIdList; //creator 엔티티 id

    private List<Long> memberIdList; //멤버id
}
