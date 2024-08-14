package com.vivace.opensw.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class ToDo extends  BaseEntity {
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
  @JsonBackReference//순환참조를 막기 위해서 넣어둠
  private Project project;


  @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
  @JoinColumn(name="member_id")
  private Member member;
  public void update(String title,String content,String status){
    this.title=title;
    this.content=content;
    this.status=status;
  }


}
