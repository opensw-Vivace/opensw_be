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
  private String team_name;
  private Date deadline;
  private String description;
  private int iteration_len;
  private String status;//처음 추가할 때 필요한가?
  private List<Participate> participateList;
  private List<ToDo> toDoList;
  private List<Issue> issueList;
  private List<Invite> inviteList;
  private List<Artifact> artifactList;
  private List<NecessaryArtifactType> necessaryArtifactTypeList;

  public Project toEntity(){
    return Project.builder()
        .title(title)
        .team_name(team_name)
        .description(description)
        .deadline(deadline)
        .iterationLen(iteration_len)
        .status(status)
        .toDoList(toDoList)
        .participateList(participateList)
        .inviteList(inviteList)
        .artifactList(artifactList)
        .issueList(issueList)
        .build();
  }
}
