package com.vivace.opensw.entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
public class ProjectEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="id",updatable = false)
  private Long id;
  @Column(name="title",nullable = false)
  private String title;
  @Column
  private String team_name;
  @Column
  private String description;
  @Column
  private Date deadline;
  @Column
  private int iterationLen;
  @Column
  private String status;

  @OneToMany(mappedBy = "projectEntity")
  private List<ParticipateEntity> participateEntityList;

  @OneToMany(mappedBy = "projectEntity")
  private List<ToDoEntity> toDoEntityList;

  @OneToMany(mappedBy = "projectEntity")
  private List<IssueEntity> issueEntityList;

  @OneToMany(mappedBy = "projectEntity")
  private List<InviteEntity> inviteEntityList;

  @OneToMany(mappedBy = "projectEntity")
  private List<ArtifactEntity> artifactEntityList;

  @OneToMany(mappedBy = "projectEntity")
  private List<NecessaryArtifactTypeEntity> necessaryArtifactTypeEntityList;





}
