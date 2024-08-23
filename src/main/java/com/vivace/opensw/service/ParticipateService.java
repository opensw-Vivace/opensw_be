package com.vivace.opensw.service;

import com.vivace.opensw.entity.Member;
import com.vivace.opensw.entity.Participate;
import com.vivace.opensw.entity.Position;
import com.vivace.opensw.entity.Project;
import com.vivace.opensw.global.exception.CustomException;
import com.vivace.opensw.global.exception.ErrorCode;
import com.vivace.opensw.repository.ParticipateRepository;
import com.vivace.opensw.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    // projectId의 프로젝트에 member가 참여중인지 확인
    @Transactional(readOnly = true)
    public List<Position> getPositionsByProjectIdAndMember(Long projectId, Member member) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(()-> new CustomException(ErrorCode.PROJECT_NOT_FOUND));
        Participate participate = getParticipateByProjectAndMember(project, member);
        return participate.getPositionList();
    }

    // Project에 Member가 참여중인지 확인
    @Transactional(readOnly = true)
    public Participate getParticipateByProjectAndMember(Project project, Member member) throws CustomException {
        return participateRepository.findByProjectAndMember(project, member)
                .orElseThrow(()-> new CustomException(ErrorCode.NOT_PARTICIPATING));
    }

}
