package com.vivace.opensw.service;

import com.vivace.opensw.entity.Issue;
import com.vivace.opensw.entity.Project;
import com.vivace.opensw.global.exception.CustomException;
import com.vivace.opensw.global.exception.ErrorCode;
import com.vivace.opensw.model.*;
import com.vivace.opensw.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Transactional
public class UPService {

    private final ProjectRepository projectRepository;
    private final ArtifactRepository artifactRepository;
    private final ToDoRepository toDoRepository;
    private final TestRepository testRepository;
    private final IssueRepository issueRepository;
    private final ProjectService projectService;

    /**
     * 특정 프로젝트의 전체 진행률 반환
     * 모든 게시물(할 일, 이슈,테스트, 산출물)이 모두 완료 처리가 되면 100% 달성.
     * 소수점 두자리까지 계산. 67.83%
     * 전체 게시물 수 계산 + 완료 처리 게시물 수 계산할 함수가 필요함.
     */
    public float getTotalPercent(Long projectId){
        //모든 게시물 개수 조회
        float totalCount = artifactRepository.countByProjectId(projectId)
                + toDoRepository.countByProjectId(projectId)
                + testRepository.countByProjectId(projectId)
                + issueRepository.countByProjectId(projectId);

        //완료된 모든 개시물 개수 조회
        float completeCount= artifactRepository.countByProjectIdAndStatus(projectId, ArtifactStatus.COMPLETED)
                + toDoRepository.countByProjectIdAndStatus(projectId, DocsStatus.COMPLETED)
                + testRepository.countByProjectIdAndStatus(projectId, TestStatus.COMPLETED)
                + issueRepository.countByProjectIdAndStatus(projectId, DocsStatus.COMPLETED);

        // 백분율로 계산
        return (float) Math.round(completeCount * 10000 / totalCount) /100;
    }

    /**
     * 특정 프로젝트의 phase진행률 반환
     * iterationLen 대비 진행된 "시간"을 기준으로 함
     */
    public float getUPPercent(Long projectId){
        Project project= projectRepository.findById(projectId)
                .orElseThrow(()->new CustomException(ErrorCode.PROJECT_NOT_FOUND));

        //얼마나 진행되었는지 날짜 계산
        float diff= LocalDate.now().compareTo(project.getIterStartDate());
        return (float) Math.round(10000 * diff / project.getIterationLen()) /100;
    }

    /**
     * 특정 프로젝트의 현재 단계 반환 (Unified Process)
     * UPStatus 클래스에 담아서 한 번에 보냄.
     */
    @Transactional(readOnly = true)
    public UPStatus getUPStatus(Long projectId){
        Project project= projectRepository.findById(projectId)
                .orElseThrow(()->new CustomException(ErrorCode.PROJECT_NOT_FOUND));

        return new UPStatus(project.getPhase(), project.getPhaseNum());
    }

    /**
     * 특정 프로젝트를 다음 phase로 넘김
     */
    public void moveNextPhase(Long projectId){
        Project project= projectRepository.findById(projectId)
                .orElseThrow(()->new CustomException(ErrorCode.PROJECT_NOT_FOUND));

        UPStatus upStatus=new UPStatus(project.getPhase(), project.getPhaseNum());

        // 현재 상태에 따라 어떤 phase로 넘길지 설정
        // enum 특성 이용해서 +1 하는 식으로 가능?
        switch (upStatus.getPhase()){
            case UPPhaseStatus.INCEPTION :
                upStatus.setPhase(UPPhaseStatus.ELABORATION);
                break;

            case UPPhaseStatus.ELABORATION:
                upStatus.setPhase(UPPhaseStatus.CONSTRUCTION);
                break;

            case UPPhaseStatus.CONSTRUCTION:
                upStatus.setPhase(UPPhaseStatus.TRANSITION);
                break;

            /**
             * Transition 다음의 단계는 없으므로.. 버튼 비활성화 가능?
              */
            case UPPhaseStatus.TRANSITION:
                upStatus.setPhase(UPPhaseStatus.TRANSITION);
                break;


        }
        upStatus.setPhaseNum(1);
        project.updateUPStatus(upStatus);
        project.clearIterStartDate();
    }

    /**
     * 특정 프로젝트를 다음 iteration으로 넘김
     */
    public void moveNextIteration(Long projectId){
        Project project= projectRepository.findById(projectId)
                .orElseThrow(()->new CustomException(ErrorCode.PROJECT_NOT_FOUND));

        // 다음 단계로 넘어가므로 iteration에 1을 더해줌
        UPStatus upStatus=new UPStatus(project.getPhase(), project.getPhaseNum()+1);


        project.updateUPStatus(upStatus);
        
        // 시작일 초기화
        project.clearIterStartDate();
    }

}


