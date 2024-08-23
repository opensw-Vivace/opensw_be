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

    // 테스트 목록 조회 (filterOnlyMine으로 내것만 볼지를 구분)
    @Transactional(readOnly = true)
    public TestListResDto getTestList(Long projectId, Boolean filterOnlyMine) {
        Member member = memberService.getCurrentMember();
        validateParticipation(member.getId(), projectId);

        Project project = projectService.findById(projectId);
        List<TestListUnitDto> notStartedTestList = getFilteredTestList(project, TestStatus.NOT_STARTED, member, filterOnlyMine);
        List<TestListUnitDto> completedTestList = getFilteredTestList(project, TestStatus.COMPLETED, member, filterOnlyMine);
        return new TestListResDto(notStartedTestList, completedTestList);
    }

    // filterOnlyMine에 따라 다르게 조회
    @Transactional(readOnly = true)
    public List<TestListUnitDto> getFilteredTestList(Project project, TestStatus status, Member member, Boolean filterOnlyMine) {
        if (filterOnlyMine) {
            return testRepository.findAllByProjectAndMemberAndStatusOrderByIdDesc(project, member, status)
                    .stream().map(TestListUnitDto::from).collect(Collectors.toList());
        } else {
            return testRepository.findAllByProjectAndStatusOrderByIdDesc(project, status)
                    .stream().map(TestListUnitDto::from).collect(Collectors.toList());
        }
    }

    // 현재 멤버가 이 프로젝트에 참여중인지 확인
    private void validateParticipation(Long memberId, Long projectId) throws CustomException {
        if (!positionRepository.existsByMemberIdAndParticipate_ProjectId(memberId, projectId)){
            throw new CustomException(ErrorCode.NOT_PARTICIPATING);
        }
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

    @Transactional(readOnly = true)
    public Test getTestById(Long testId) throws CustomException {
        return testRepository.findById(testId)
                .orElseThrow(() -> new CustomException(ErrorCode.TEST_NOT_FOUND));
    }
}
