package com.vivace.opensw.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class ArtifactType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String type; //산출물 종류.

    @OneToMany(mappedBy = "artifactType")
    private List<NecessaryArtifactType> necessaryArtifactTypeList;

    @OneToMany(mappedBy = "artifactType")
    private List<Artifact> artifactList;
}
