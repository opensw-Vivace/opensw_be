package com.vivace.opensw.service;


import com.vivace.opensw.dto.test.*;
import com.vivace.opensw.entity.Member;
import com.vivace.opensw.entity.Project;
import com.vivace.opensw.entity.Test;
import com.vivace.opensw.global.exception.CustomException;
import com.vivace.opensw.global.exception.ErrorCode;
import com.vivace.opensw.model.TestStatus;
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

    // 테스트 작성
    public void addTest(Long projectId, TestAddReqDto reqDto) {
        Member member = memberService.getCurrentMember();
        Project project = projectService.findById(projectId);
        Test test = TestAddReqDto.toEntity(reqDto, project, member);
        testRepository.save(test);
    }

    // 테스트 목록 조회
    @Transactional(readOnly = true)
    public TestListResDto getTestList(Long projectId) {
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
        validateIsMine(test);
        test.modifyInfo(reqDto.title(), reqDto.content(), reqDto.status());
        testRepository.save(test);
    }

    // 테스트 삭제
    public void deleteTest(Long testId) {
        Test test = getTestById(testId);
        validateIsMine(test);
        testRepository.delete(test);
    }

    // 테스트가 내 것인지 권한 확인 (수정, 삭제)
    private void validateIsMine(Test test) {
        if (!test.getMember().equals(memberService.getCurrentMember())) {
            throw new CustomException(ErrorCode.UNAUTHORIZED_REQUEST);
        }
    }

    @Transactional(readOnly = true)
    public List<Test> getTestsByProject(Project project, TestStatus status) {
        return testRepository.findAllByProjectAndStatus(project, status);
    }

    @Transactional(readOnly = true)
    public Test getTestById(Long testId) {
        return testRepository.findById(testId)
                .orElseThrow(() -> new CustomException(ErrorCode.TEST_NOT_FOUND));
    }
}
