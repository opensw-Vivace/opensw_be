package com.vivace.opensw.controller;

import com.vivace.opensw.dto.project.ProjectAddRequestDto;
import com.vivace.opensw.dto.project.ProjectListViewResponseDto;
import com.vivace.opensw.entity.Member;
import com.vivace.opensw.entity.Participate;
import com.vivace.opensw.service.MemberService;
import com.vivace.opensw.service.ProjectService;

import com.vivace.opensw.entity.Project;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequiredArgsConstructor
@RestController//겍체 데이터를 json으로 반환하기 위해서 추가함
public class ProjectController {

  private final ProjectService projectService;


  @PostMapping("/projects")//projects를 addproject와 매핑
  public ResponseEntity<Project> addProject(@RequestBody ProjectAddRequestDto projectAddRequestDto){
    Project savedProject= projectService.save(projectAddRequestDto);
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(savedProject);

  }

  @GetMapping("/projects")
  public ResponseEntity<List<ProjectListViewResponseDto>> getProjects() {
    List<ProjectListViewResponseDto> projects = projectService.findAll()
        .stream()
        .map(ProjectListViewResponseDto::from)
        .toList();

    return ResponseEntity.status(HttpStatus.CREATED)
        .body(projects);
  }

  @GetMapping("/projects/{projectId}")
  public ResponseEntity<ProjectListViewResponseDto> getProject(@PathVariable("projectId") Long id){
    Project project=projectService.findById(id);
    ProjectListViewResponseDto projectDto=ProjectListViewResponseDto.from(project);
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(projectDto);
  }
  @GetMapping("/projects/{projectId}/members")
  public ResponseEntity<List<Participate>> getProjectMembers(@PathVariable("projectId") Long id){
    Project project=projectService.findById(id);
    if(project==null){
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
    List<Participate> participates=projectService.getProjectParticipants(id);
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(participates);
  }



  @DeleteMapping("/projects/{id}")
  public ResponseEntity<Void> deleteProjects(@PathVariable long id){
    projectService.deleteById(id);
    return ResponseEntity.ok().build();
  }


}
