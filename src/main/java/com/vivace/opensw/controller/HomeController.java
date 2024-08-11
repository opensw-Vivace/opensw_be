package com.vivace.opensw.controller;

import com.vivace.opensw.service.ProjectService;
import com.vivace.opensw.dto.project.ProjectAddRequestDto;
import com.vivace.opensw.entity.Project;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController//겍체 데이터를 json으로 반환하기 위해서 추가함
public class HomeController {

  private final ProjectService projectService;

  @PostMapping("/api/projects")//api/projects를 addproject와 매핑
  public ResponseEntity<Project> addProject(@RequestBody ProjectAddRequestDto addProject){
    Project savedProject= projectService.save(addProject);
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(savedProject);

  }


  @DeleteMapping("/api/projects/{id}")
  public ResponseEntity<Void> deleteProjects(@PathVariable long id){
    projectService.deleteById(id);
    return ResponseEntity.ok().build();
  }


}
