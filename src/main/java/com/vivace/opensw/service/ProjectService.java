package com.vivace.opensw.service;

import com.vivace.opensw.dto.project.ProjectAddRequestDto;
import com.vivace.opensw.entity.Participate;
import com.vivace.opensw.entity.Position;
import com.vivace.opensw.entity.Project;
import com.vivace.opensw.global.exception.CustomException;
import com.vivace.opensw.global.exception.ErrorCode;
import com.vivace.opensw.repository.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.vivace.opensw.model.Role.ROLE_OWNER;

@RequiredArgsConstructor
@Service
public class ProjectService {

  private final ProjectRepository projectRepository;
  private final MemberService memberService;
  private final ParticipateRepository participateRepository;
  private final PositionRepository positionRepository;

  @Transactional
  public Project save(ProjectAddRequestDto addProject) {
    Project project=projectRepository.save(addProject.toEntity());
    List<Position> positionList=new ArrayList<>();
    Participate participate= Participate.builder()
            .member(memberService.getCurrentMember())
            .project(project).role(ROLE_OWNER)
            .build();
    participate = participateRepository.save(participate);
    for(String positionName: addProject.getPositionName()){
      Position position= Position.builder().positionName(positionName)
              .participate(participate)
              .build();

      positionRepository.save(position);
      positionList.add(position);
    }
    participate.updatePosition(positionList);

    project.getParticipateList().add(participate);
    return project;
  }

  public Project findById(Long id){
    return projectRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.PROJECT_NOT_FOUND));
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