package com.vivace.opensw.entity;

import jakarta.persistence.*;

import java.awt.*;

@Entity
public class Img extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String path;

    @ManyToOne(fetch =FetchType.LAZY,cascade = CascadeType.REMOVE)
    @JoinColumn(name="artifact_id")
    private Artifact artifact;


}
