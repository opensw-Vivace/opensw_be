package com.vivace.opensw.entity;

import com.vivace.opensw.global.BaseEntity;
import com.vivace.opensw.model.ArtifactStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Artifact extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Long id;

    @Column
    @NotNull
    @NotBlank
    private String title;

    @Column
    @NotNull
    private String subtitle;

    @Column
    @NotNull
    @Enumerated(EnumType.STRING)
    private ArtifactStatus status;

    @Column
    @NotNull
    private LocalDate deadline;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "project_id", updatable = false)
    private Project project;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "artifact_type_id") // 변경 불가능하지 않을 수도
    private ArtifactType artifactType;


    @OneToMany(mappedBy = "artifact", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Img> imgList;

    @OneToMany(mappedBy = "artifact", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<ArtifactCreator> creatorList;

    public void updateImgList(List<Img> imgList){
        this.imgList=imgList;
    }

    public void updateCreatorList(List<ArtifactCreator> creatorList){
        this.creatorList=creatorList;
    }
}
