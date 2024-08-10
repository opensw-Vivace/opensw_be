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
public class Artifact extends BaseEntity{
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
  private Project project;

  @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
  @JoinColumn(name = "artifact_type_id")
  private ArtifactType artifactType;

  @OneToMany(mappedBy = "artifact")
  private List<Img> imgList;

  @OneToMany(mappedBy = "artifact")
  private List<Creator> creatorList;

}
