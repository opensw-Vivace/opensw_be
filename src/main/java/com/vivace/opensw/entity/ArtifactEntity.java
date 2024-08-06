package com.vivace.opensw.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.processing.Generated;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class ArtifactEntity extends BaseEntity{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column
  private Long id;
  @Column
  private String title;
  @Column
  private String subtitle;
  @Column
  private String status;

  @Column
  private Date deadline;
  @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
  @JoinColumn(name = "project_id")
  ProjectEntity projectEntity;

  @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
  @JoinColumn(name = "artifactType_id")
  ArtifactTypeEntity artifactTypeEntity;

  @OneToMany(mappedBy = "imgEntity_id")
  private List<ImgEntity> imgEntityList;

  @OneToMany(mappedBy = "creatorEntity_id")
  private List<CreatorEntity> creatorEntityList;

}
