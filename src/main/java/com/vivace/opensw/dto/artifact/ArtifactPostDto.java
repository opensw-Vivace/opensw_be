package com.vivace.opensw.dto.artifact;

import com.vivace.opensw.model.ArtifactStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
/**
 * post 요청 시(글 작성 시)사용하는 dto.
 */
public class ArtifactPostDto {

    private String title;

    private String subtitle;

    private ArtifactStatus status;

    private LocalDate deadline;

    private Long projectId;

    private Long artifactTypeId;

    private List<String> imgPathList;

    private List<Long> writerIdList; //작성자 id리스트.

}
