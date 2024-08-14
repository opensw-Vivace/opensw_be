package com.vivace.opensw.dto;

import com.vivace.opensw.model.DocsStatus;
import lombok.Getter;

@Getter
public class UpdateToDo {
  private String title;
  private String content;
  private DocsStatus status;

}
