package com.vivace.opensw.dto.todo;

import com.vivace.opensw.entity.ToDo;
import com.vivace.opensw.model.DocsStatus;
import lombok.*;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ToDoList{
  private Long ProjectId;
  private String title;
  private String content;
  private DocsStatus status;

}
