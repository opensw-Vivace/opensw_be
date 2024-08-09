package com.vivace.opensw.dto;

import com.vivace.opensw.entity.Project;
import lombok.Getter;

import java.util.Date;

@Getter
public class ProjectListView {
  private final Long id;
  private final String title;
  private final String description;
  private final Date deadline;

  public ProjectListView(Project projectEntity){
    this.id=projectEntity.getId();
    this.title=projectEntity.getTitle();
    this.description=projectEntity.getDescription();
    this.deadline=projectEntity.getDeadline();
  }

}
