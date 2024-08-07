package com.vivace.opensw.entity;

import jakarta.persistence.*;

@Entity
public class ImgEntity extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "artifact_id")
    private ArtifactEntity artifactEntity;

}
