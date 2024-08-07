package com.vivace.opensw.Service;

import com.vivace.opensw.dto.AddProject;
import com.vivace.opensw.entity.ProjectEntity;
import com.vivace.opensw.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ProjectService {
  private final ProjectRepository projectRepository;
  public ProjectEntity save(AddProject addProject){//생성시 프로젝트 저장
     return projectRepository.save(addProject.toEntity());
  }
}
