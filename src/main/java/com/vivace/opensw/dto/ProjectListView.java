package com.vivace.opensw.dto;

import com.vivace.opensw.entity.Project;
import com.vivace.opensw.entity.ToDo;
import lombok.Getter;

import java.util.Date;
import java.util.List;

@Getter
public class ProjectListView {
  private final Long id;
  private final String title;
  private final String description;
  private final Date deadline;
  private final List<ToDo> todo;

  public ProjectListView(Project projectEntity){
    this.id=projectEntity.getId();
    this.title=projectEntity.getTitle();
    this.description=projectEntity.getDescription();
    this.deadline=projectEntity.getDeadline();
    this.todo=projectEntity.getToDoList();
  }

}
