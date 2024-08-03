package com.vivace.opensw.entity;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
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


}
