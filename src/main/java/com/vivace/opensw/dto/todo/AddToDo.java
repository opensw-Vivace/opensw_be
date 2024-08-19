package com.vivace.opensw.dto.todo;

import com.vivace.opensw.entity.Project;
import com.vivace.opensw.entity.ToDo;
import com.vivace.opensw.model.DocsStatus;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class AddToDo {
  private String title;
  private String content;
  private DocsStatus status;
  private Long projectId;
  //나의 할일
}
