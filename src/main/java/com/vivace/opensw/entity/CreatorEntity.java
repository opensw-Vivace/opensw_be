package com.vivace.opensw.entity;

import jakarta.persistence.*;

public class CreatorEntity extends BaseEntity{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="id",updatable = false)
  private Long id;

  @Column
  Boolean complete;
  @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
  @JoinColumn(name = "member_id")
  MemberEntity memberEntity;
  @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
  @JoinColumn(name="artifact_id")
  ArtifactEntity artifactEntity;

}
