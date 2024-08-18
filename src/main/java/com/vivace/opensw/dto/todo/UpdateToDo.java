package com.vivace.opensw.dto.todo;

import com.vivace.opensw.model.DocsStatus;
import lombok.Getter;

@Getter
public class UpdateToDo {
  private Long id;
  private String title;
  private String content;
  private DocsStatus status;
  private Long projectId;

}
