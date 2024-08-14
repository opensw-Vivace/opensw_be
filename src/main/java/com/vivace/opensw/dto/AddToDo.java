package com.vivace.opensw.dto;

import com.vivace.opensw.entity.Project;
import com.vivace.opensw.entity.ToDo;
import com.vivace.opensw.model.DocsStatus;
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
  private DocsStatus status;
  private Long projectId;
  public ToDo toEntity(Project project){
    return ToDo.builder().
        title(title)
        .content(content)
        .status(status)
        .project(project)
        .build();
  }
}
