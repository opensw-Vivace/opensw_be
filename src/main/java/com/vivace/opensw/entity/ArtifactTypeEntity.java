package com.vivace.opensw.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class ArtifactTypeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String type; //산출물 종류.

    @OneToMany(mappedBy = "artifactTypeEntity")
    private List<NecessaryArtifactTypeEntity> necessaryArtifactTypeEntityList;

    @OneToMany(mappedBy = "artifactTypeEntity")
    private List<ArtifactEntity> artifactEntityList;
}
