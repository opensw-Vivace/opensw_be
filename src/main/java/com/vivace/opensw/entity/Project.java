package com.vivace.opensw.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder//빌더패턴으로 객체 생성을 위해서
public class Project {
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

  @OneToMany(mappedBy = "project")
  private List<Participate> participateList;

  @OneToMany(mappedBy = "project")
  private List<ToDo> toDoList;

  @OneToMany(mappedBy = "project")
  private List<Issue> issueList;

  @OneToMany(mappedBy = "project")
  private List<Invite> inviteList;

  @OneToMany(mappedBy = "project")
  private List<Artifact> artifactList;

  @OneToMany(mappedBy = "project")
  private List<NecessaryArtifactType> necessaryArtifactTypeList;



}
