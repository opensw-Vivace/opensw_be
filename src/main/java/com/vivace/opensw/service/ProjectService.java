package com.vivace.opensw.service;

import com.vivace.opensw.dto.project.ProjectAddRequestDto;
import com.vivace.opensw.dto.project.ProjectGetMembersDto;
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
  private final ArtifactRepository artifactRepository;
  private final ProjectRepository projectRepository;
  private final ArtifactTypeRepository artifactTypeRepository;
  private final MemberService memberService;
  private final ParticipateRepository participateRepository;
  private final PositionRepository positionRepository;
  @Transactional
  public Project save(ProjectAddRequestDto addProject) {//프로젝트 생성 메서드
    Project project=projectRepository.save(addProject.toEntity());

    List<Position> positionList=new ArrayList<>();

    Participate participate= Participate.builder().
        project(project).role(ROLE_OWNER)
        .build();
    participate = participateRepository.save(participate);

    for(String positionName: addProject.getPositionName()){
      Position position= Position.builder().position(positionName).
          member(memberService.getCurrentMember())
          .participate(participate)
          .build();

      positionRepository.save(position);
      positionList.add(position);
    }
    participate.updatePosition(positionList);
    project.getParticipateList().add(participate);
    return project;
  }

  public Project findById(Long id){//특정 프로젝트 찾기
    return projectRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.PROJECT_NOT_FOUND));
  }

  public List<Project> findAll(){
    return projectRepository.findAll();
  }

  public void deleteById(long id){
    projectRepository.deleteById(id);
  }
  public List<ProjectGetMembersDto> getProjectParticipants(Long id){//프로젝트 멤버 조회
    //매개변수 프로젝트 아이디
    Project project=projectRepository.findById(id).
         orElseThrow(()->new CustomException(ErrorCode.PROJECT_NOT_FOUND));
    List<Participate> participates=project.getParticipateList();
    List<ProjectGetMembersDto> result=new ArrayList<>();


    for(Participate participate:participates){
      List<String> positions=new ArrayList<>();
      String membername=null;
      for(Position position:participate.getPositionList()){
        if(membername==null){
          membername=position.getMember().getName();
        }
        positions.add(position.getPosition());
      }
      result.add(new ProjectGetMembersDto(membername,positions));
    }

    System.out.println(memberService.getCurrentMember().getId());
    return result;
  }


}