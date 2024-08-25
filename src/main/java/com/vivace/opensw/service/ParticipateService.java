package com.vivace.opensw.service;

import com.vivace.opensw.entity.Member;
import com.vivace.opensw.entity.Participate;
import com.vivace.opensw.entity.Position;
import com.vivace.opensw.entity.Project;
import com.vivace.opensw.global.exception.CustomException;
import com.vivace.opensw.global.exception.ErrorCode;
import com.vivace.opensw.model.Role;
import com.vivace.opensw.repository.ParticipateRepository;
import com.vivace.opensw.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.vivace.opensw.model.Role.ROLE_OWNER;

@Service
@RequiredArgsConstructor
public class ParticipateService {
    private final ParticipateRepository participateRepository;
    private final ProjectRepository projectRepository;

    // memberId의 Member가 이 프로젝트에 참여중인지 확인
    @Transactional(readOnly = true)
    public void validateParticipation(Long projectId, Long memberId) throws CustomException {
        if (!participateRepository.existsByProjectIdAndMemberId(projectId, memberId)){
            throw new CustomException(ErrorCode.NOT_PARTICIPATING);
        }
    }

    // project와 member로 participate를 찾음
    @Transactional(readOnly = true)
    public Participate getParticipateByProjectAndMember(Project project, Member member) throws CustomException {
        return participateRepository.findByProjectAndMember(project, member)
                .orElseThrow(()-> new CustomException(ErrorCode.NOT_PARTICIPATING));
    }

    // project로 participate를 찾음
    @Transactional(readOnly = true)
    public List<Participate> getParticipatesByProject(Project project) throws CustomException {
        return participateRepository.findAllByProject(project);
    }


    // member로 participate를 찾음
    @Transactional(readOnly = true)
    public List<Participate> getParticipatesByMember(Member member) throws CustomException {
        return participateRepository.findAllByMember(member);
    }


    @Transactional
    public Participate save(Member member, Project project, Role role) {
        Participate participate = Participate.builder()
                .member(member)
                .project(project).role(role)
                .build();
        return participateRepository.save(participate);
    }
}
