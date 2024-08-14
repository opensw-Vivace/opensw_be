package com.vivace.opensw.service;

import com.vivace.opensw.dto.project.ProjectAddRequestDto;
import com.vivace.opensw.entity.Project;
import com.vivace.opensw.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProjectService {


  private final ProjectRepository projectRepository;



  public Project save(ProjectAddRequestDto addProject) {//생성시 프로젝트 저장
     return projectRepository.save(addProject.toEntity(addProject));

  }
  public Project findById(Long id){
    return projectRepository.findById(id).orElseThrow(()->new IllegalArgumentException("not found: "+id));
  }

  public List<Project> findAll(){
    return projectRepository.findAll();
  }

  public void deleteById(long id){
    projectRepository.deleteById(id);
  }
  public List<Participate> getProjectParticipants(Long id){
    //매개변수 프로젝트 아이디
    Project project=findById(id);
    if(project!=null){
      return project.getParticipateList();
    }
    else
      return null;
  }


}
