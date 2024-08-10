package com.vivace.opensw.dto;

import com.vivace.opensw.entity.ArtifactType;
import com.vivace.opensw.entity.Creator;
import com.vivace.opensw.entity.Img;
import com.vivace.opensw.entity.Project;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
public class ArtifactDto {

    private Long id;

    private String title;

    private String subtitle;

    private String status;


    private Date deadline;


    private Long projectId;


    private Long artifactTypeId;


    private List<String> imgPathList;

    private List<Long> creatorId; //creator의 인덱스 리스트. 작성자 id리스트가 아님 유의.

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
