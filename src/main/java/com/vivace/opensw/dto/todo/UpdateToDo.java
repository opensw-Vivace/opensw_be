package com.vivace.opensw.dto.todo;

import com.vivace.opensw.model.DocsStatus;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class UpdateToDo {

  private String title;
  private String content;
  private DocsStatus status;
  private Long projectId;

}
