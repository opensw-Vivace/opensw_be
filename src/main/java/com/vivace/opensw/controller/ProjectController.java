package com.vivace.opensw.controller;

import com.vivace.opensw.dto.project.ProjectAddReqDto;

import com.vivace.opensw.dto.project.ProjectListViewResDto;
import com.vivace.opensw.dto.project.ProjectMemberInfoDto;
import com.vivace.opensw.service.ProjectService;

import com.vivace.opensw.entity.Project;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController//겍체 데이터를 json으로 반환하기 위해서 추가함
public class ProjectController {

  private final ProjectService projectService;

  @PostMapping("/projects")//projects를 addproject와 매핑
  public ResponseEntity<Project> addProject(@RequestBody ProjectAddReqDto projectAddReqDto){
    Project savedProject= projectService.save(projectAddReqDto);
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(savedProject);

  }

//  @GetMapping("/projects")
//  public ResponseEntity<List<ProjectListViewResDto>> getProjects() {
//    List<ProjectListViewResDto> projects = projectService.findAll()
//        .stream()
//        .map(ProjectListViewResDto::from)
//        .toList();
//
//    return ResponseEntity.status(HttpStatus.CREATED)
//        .body(projects);
//  }

  @GetMapping("/projects/{projectId}")
  public ResponseEntity<ProjectListViewResDto> getProject(@PathVariable("projectId") Long id){
    Project project=projectService.findById(id);
    ProjectListViewResDto projectDto= ProjectListViewResDto.from(project);
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(projectDto);
  }
  @GetMapping("/projects/{projectId}/members")
  public ResponseEntity<List<ProjectMemberInfoDto>> getProjectMembers(@PathVariable("projectId") Long id){
    List<ProjectMemberInfoDto> participants = projectService.getProjectParticipants(id);
    return ResponseEntity.status(HttpStatus.OK).body(participants);
  }



  @DeleteMapping("/projects/{id}")
  public ResponseEntity<Void> deleteProjects(@PathVariable Long id){
    projectService.deleteById(id);
    return ResponseEntity.ok().build();
  }

  /**
   * 현재 로그인 한 멤버가 참여중인 프로젝트 dto 반환
   */
  @GetMapping("/projects")
  public ResponseEntity<List<ProjectListViewResDto>> getMyProject(){
    return ResponseEntity.status(HttpStatus.OK).body(projectService.getMyProject());
  }

  @GetMapping ("/projects/{projectId}/me")
  public ResponseEntity<?> getMyInfoInProject(@PathVariable final Long projectId) {
    return ResponseEntity.ok().body(projectService.getMyInfoInProject(projectId));
  }

}
