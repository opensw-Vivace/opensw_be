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





}
