package com.vivace.opensw.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder // 빌더 패턴
//ORM -> Java(다른언어) Object -> 테이블로 매핑해주는 기술
@Entity //
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
  ProjectEntity projectEntity;


  @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
  @JoinColumn(name="Member_id")
  MemberEntity memberEntity;







}
