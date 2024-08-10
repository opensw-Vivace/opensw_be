package com.vivace.opensw.controller;
import com.vivace.opensw.dto.ProjectListView;
import com.vivace.opensw.service.ProjectService;
import com.vivace.opensw.dto.AddProject;
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
  public ResponseEntity<Project> addProject(@RequestBody AddProject addProject){
    Project savedProject= projectService.save(addProject);
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(savedProject);

  }

  @GetMapping("/projects")
  public ResponseEntity<List<ProjectListView>> getProjects() {
    List<ProjectListView> projects = projectService.findAll()
        .stream()
        .map(ProjectListView::new)
        .toList();

    return ResponseEntity.status(HttpStatus.CREATED)
        .body(projects);
  }

  @GetMapping("/projects/{projectId}")
  public String getProject(@PathVariable("projectId") Long id,Model model){
    Project project=projectService.findById(id);
    ProjectListView projectDto=new ProjectListView(project);
    model.addAttribute("project",projectDto);
    return "project";
  }


  @DeleteMapping("/api/projects/{id}")
  public ResponseEntity<Void> deleteProjects(@PathVariable long id){
    projectService.deleteById(id);
    return ResponseEntity.ok().build();
  }


}
