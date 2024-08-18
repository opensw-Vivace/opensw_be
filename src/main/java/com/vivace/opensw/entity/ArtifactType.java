package com.vivace.opensw.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class ArtifactType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Long id;

    @Column
    @NotNull
    @NotBlank
    private String type; //산출물 종류.

    @OneToMany(mappedBy = "artifactType")
    private List<NecessaryArtifactType> necessaryArtifactTypeList;

    @OneToMany(mappedBy = "artifactType")
    private List<Artifact> artifactList;
}
