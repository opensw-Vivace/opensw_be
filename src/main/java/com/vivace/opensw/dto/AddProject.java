package com.vivace.opensw.dto;

import com.vivace.opensw.entity.*;
import jakarta.persistence.OneToMany;
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
  private List<ParticipateEntity> participateEntityList;
  private List<ToDoEntity> toDoEntityList;
  private List<IssueEntity> issueEntityList;
  private List<InviteEntity> inviteEntityList;
  private List<ArtifactEntity> artifactEntityList;
  private List<NecessaryArtifactTypeEntity> necessaryArtifactTypeEntityList;

  public ProjectEntity toEntity(){
    return ProjectEntity.builder()
        .title(title)
        .team_name(team_name)
        .description(description)
        .deadline(deadline)
        .iterationLen(iteration_len)
        .status(status)
        .toDoEntityList(toDoEntityList)
        .participateEntityList(participateEntityList)
        .inviteEntityList(inviteEntityList)
        .artifactEntityList(artifactEntityList)
        .issueEntityList(issueEntityList)
        .build();
  }
}
