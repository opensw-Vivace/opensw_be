package com.vivace.opensw.entity;

import jakarta.persistence.*;

@Entity
public class ArtifactTypeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String type; //산출물 종류.
}
