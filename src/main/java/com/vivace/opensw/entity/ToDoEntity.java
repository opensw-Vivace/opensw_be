package com.vivace.opensw.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class ToDoEntity extends  BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column
  private Long id;
  @Column
  private String title;
  @Column
  private String content;
  @Column
  private String status;
  @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
  @JoinColumn(name="project_id")
  private ProjectEntity projectEntity;


  @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
  @JoinColumn(name="member_id")
  private MemberEntity memberEntity;

}
