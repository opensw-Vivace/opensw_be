package com.vivace.opensw.dto;

import com.vivace.opensw.entity.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AddProject {
  private String title;
  private Date deadline;
  private String description;
  //처음 추가할 때 필요한가?

  public Project toEntity(){
    return Project.builder()
        .title(title)
        .description(description)
        .deadline(deadline)
        .build();
  }
}
