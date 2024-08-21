package com.vivace.opensw.service;


import com.vivace.opensw.dto.test.*;
import com.vivace.opensw.entity.Member;
import com.vivace.opensw.entity.Project;
import com.vivace.opensw.entity.Test;
import com.vivace.opensw.global.exception.CustomException;
import com.vivace.opensw.global.exception.ErrorCode;
import com.vivace.opensw.model.TestStatus;
import com.vivace.opensw.repository.PositionRepository;
import com.vivace.opensw.repository.TestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class TestService {

    private final MemberService memberService;
    private final ProjectService projectService;
    private final TestRepository testRepository;
    private final PositionRepository positionRepository;

    // 테스트 작성
    public void addTest(Long projectId, TestAddReqDto reqDto) {
        Member member = memberService.getCurrentMember();
        validateParticipation(member.getId(), projectId);

        Project project = projectService.findById(projectId);
        Test test = TestAddReqDto.toEntity(reqDto, project, member);
        testRepository.save(test);
    }

    // 테스트 목록 조회
    @Transactional(readOnly = true)
    public TestListResDto getTestList(Long projectId) {
        Member member = memberService.getCurrentMember();
        validateParticipation(member.getId(), projectId);

        Project project = projectService.findById(projectId);
        List<TestListUnitDto> notStartedTestList = getTestsByProject(project, TestStatus.NOT_STARTED)
                .stream().map(TestListUnitDto::from).collect(Collectors.toList());
        List<TestListUnitDto> completedTestList = getTestsByProject(project, TestStatus.COMPLETED)
                .stream().map(TestListUnitDto::from).collect(Collectors.toList());
        return new TestListResDto(notStartedTestList, completedTestList);
    }

    // 개별 테스트 상세 조회
    @Transactional(readOnly = true)
    public TestDetailResDto getTestDetail(Long testId) {
        Test test = getTestById(testId);
        return TestDetailResDto.from(test);
    }

    // 테스트 수정
    public void motifyTest(Long testId, TestUpdateReqDto reqDto) {
        Test test = getTestById(testId);
        test.modifyInfo(reqDto.title(), reqDto.content(), reqDto.status());
        testRepository.save(test);
    }

    // 테스트 삭제
    public void deleteTest(Long testId) {
        Test test = getTestById(testId);
        test.clearAssociations();
        testRepository.delete(test);
    }

    // 현재 멤버가 이 프로젝트에 참여중인지 확인
    private void validateParticipation(Long memberId, Long projectId)
    {
        if (!positionRepository.existsByMemberIdAndParticipate_ProjectId(memberId, projectId)){
            throw new CustomException(ErrorCode.NOT_PARTICIPATING);
        }
    }

    @Transactional(readOnly = true)
    public List<Test> getTestsByProject(Project project, TestStatus status) {
        return testRepository.findAllByProjectAndStatusOrderByIdDesc(project, status);
    }

    @Transactional(readOnly = true)
    public Test getTestById(Long testId) {
        return testRepository.findById(testId)
                .orElseThrow(() -> new CustomException(ErrorCode.TEST_NOT_FOUND));
    }
}
