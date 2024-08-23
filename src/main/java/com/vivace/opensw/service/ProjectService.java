package com.vivace.opensw.service;

import com.vivace.opensw.dto.project.ProjectAddReqDto;
import com.vivace.opensw.dto.project.ProjectListViewResDto;
import com.vivace.opensw.dto.project.ProjectMemberInfoResDto;
import com.vivace.opensw.entity.Member;
import com.vivace.opensw.entity.Participate;
import com.vivace.opensw.entity.Position;
import com.vivace.opensw.entity.Project;
import com.vivace.opensw.global.exception.CustomException;
import com.vivace.opensw.global.exception.ErrorCode;
import com.vivace.opensw.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.vivace.opensw.model.Role.ROLE_OWNER;

@RequiredArgsConstructor
@Service
public class ProjectService {
    private final ArtifactRepository artifactRepository;
    private final ProjectRepository projectRepository;
    private final ArtifactTypeRepository artifactTypeRepository;
    private final MemberService memberService;
    private final PositionRepository positionRepository;
    private final ParticipateService participateService;

    @Transactional
    public Project save(ProjectAddReqDto projectAddReqDto) {//프로젝트 생성 메서드
        Member member = memberService.getCurrentMember();
        Project project = projectRepository.save(projectAddReqDto.toEntity());

        List<Position> positionList = new ArrayList<>();
        Participate participate = participateService.save(member, project, ROLE_OWNER);

        for (String positionName : projectAddReqDto.getPositionName()) {
            Position position = Position.builder().positionName(positionName)
                    .participate(participate)
                    .build();

            positionRepository.save(position);
            positionList.add(position);
        }
        participate.updatePosition(positionList);
        project.getParticipateList().add(participate);
        return project;
    }

    public Project findById(Long id) {//특정 프로젝트 찾기
        return projectRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.PROJECT_NOT_FOUND));
    }

    public void deleteById(long id) {
        projectRepository.deleteById(id);
    }

    //프로젝트 멤버 조회
    @Transactional(readOnly = true)
    public List<ProjectMemberInfoResDto> getProjectParticipants(Long id) {
        //매개변수 프로젝트 아이디
        Project project = findById(id);

        List<Participate> participateList = participateService.getParticipatesByProject(project);

        List<ProjectMemberInfoResDto> memberDtoList = participateList.stream()
                .map(participate -> {
                    String name = participate.getMember().getName();
                    List<String> positionNameList = participate.getPositionList().stream()
                            .map(position -> position.getPositionName())
                            .toList();
                    return new ProjectMemberInfoResDto(name, participate.getRole(), positionNameList);
                })
                .toList();

        return memberDtoList;
    }

    // 프로젝트에서의 나의 정보
    @Transactional(readOnly = true)
    public ProjectMemberInfoResDto getMyInfoInProject(Long id) {
        Project project = findById(id);
        Member member = memberService.getCurrentMember();

        Participate participate = participateService.getParticipateByProjectAndMember(project, member);

        // 포지션명이 담긴 문자열 리스트로 변환
        List<String> positionNameList = participate.getPositionList().stream()
                .map(position -> position.getPositionName())
                .toList();

        return ProjectMemberInfoResDto.from(member, participate.getRole(), positionNameList);
    }

    /**
     * 내가 참여중인 프로젝트 리스트 반환
     */
    @Transactional(readOnly = true)
    public List<ProjectListViewResDto> getMyProject() {
        //현재 멤버가 참여중인 모든 포지션 리스트 가져옴
        List<Participate> participateList = participateService.getParticipatesByMember(memberService.getCurrentMember());

        List<ProjectListViewResDto> projectListViewResDtoList = participateList.stream()
                .map(participate -> ProjectListViewResDto.from(participate.getProject()))
                .collect(Collectors.toList());
        return projectListViewResDtoList;
    }
}