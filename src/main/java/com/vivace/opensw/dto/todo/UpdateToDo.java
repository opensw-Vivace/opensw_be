package com.vivace.opensw.dto.todo;

import com.vivace.opensw.model.DocsStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Setter
@Builder
public class UpdateToDo {
  private Long id;
  private String title;
  private String content;
  private DocsStatus status;
  private Long projectId;
  private Long memberId;
}
