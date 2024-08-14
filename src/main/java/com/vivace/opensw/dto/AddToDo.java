package com.vivace.opensw.dto;

import com.vivace.opensw.entity.Project;
import com.vivace.opensw.entity.ToDo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AddToDo {
  private String title;
  private String content;
  private String status;
  private Long id;
  private Long ProjectId;
  public ToDo toEntity(Project project){
    return ToDo.builder().
        title(title)
        .content(content)
        .status(status)
        .id(id)
        .project(project)
        .build();
  }
}
