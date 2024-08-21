package com.vivace.opensw.service;

import com.vivace.opensw.dto.project.ProjectAddRequestDto;
import com.vivace.opensw.dto.project.ProjectListViewResponseDto;
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
  private final ImgRepository imgRepository;
  private final CreatorRepository creatorRepository;
  private final MemberRepository memberRepository;
  private final MemberService memberService;
  private final ParticipateRepository participateRepository;
  private final PositionRepository positionRepository;

  @Transactional
  public Project save(ProjectAddRequestDto addProject) {//
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

  /**
   * 내가 참여중인 프로젝트 리스트 반환
   */
  public List<ProjectListViewResponseDto> getMyProject(){

      //현재 멤버가 참여중인 모든 포지션 리스트 가져옴
      List<Position> positionList=positionRepository.findAllByMember(memberService.getCurrentMember())
              .orElseThrow(()->new CustomException(ErrorCode.POSITION_NOT_FOUND));

      // position으로부터 프로젝트 리스트 생성
      // position -> participate -> project
      Set<Project> projectSet=new LinkedHashSet<>(); //중복 제거를 위해 set사용
      for(Position position:positionList){
        projectSet.add(position.getParticipate().getProject());
      }

      //dto로 변경해서 출력
      List<ProjectListViewResponseDto> projectListViewResponseDtoList=new ArrayList<>();
      for(Project project: projectSet){
        projectListViewResponseDtoList.add(ProjectListViewResponseDto.from(project));
      }

      return projectListViewResponseDtoList;
  }



}