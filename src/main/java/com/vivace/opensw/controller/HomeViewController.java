package com.vivace.opensw.controller;

import com.vivace.opensw.service.ProjectService;
import com.vivace.opensw.dto.ProjectListView;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
@RequiredArgsConstructor
@Controller
public class HomeViewController {
  private final ProjectService projectService;
  @GetMapping("/projects")
  public String getProjects(Model model){
    List<ProjectListView> projects=projectService.findAll()
        .stream()
        .map(ProjectListView::new)
        .toList();
    model.addAttribute("projects",projects);
    return "projectList";
  }
  @GetMapping("/projects/{id}")
  public String getProject(@PathVariable Long id)
}
