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
  private ProjectEntity projectEntity;

  @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
  @JoinColumn(name = "artifactType_id")
  private ArtifactTypeEntity artifactTypeEntity;

  @OneToMany(mappedBy = "artifactEntity")
  private List<ImgEntity> imgEntityList;

  @OneToMany(mappedBy = "artifactEntity")
  private List<CreatorEntity> creatorEntityList;

}
