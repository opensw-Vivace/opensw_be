package com.vivace.opensw.controller;

import com.vivace.opensw.dto.test.TestAddReqDto;
import com.vivace.opensw.dto.test.TestUpdateReqDto;
import com.vivace.opensw.service.TestService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class TestController {
    private final TestService testService;

    // 테스트 작성
    @PostMapping("/projects/{projectId}/tests")
    public ResponseEntity<?> addTest(@PathVariable final Long projectId, @RequestBody @Valid TestAddReqDto testAddReqDto) {
        testService.addTest(projectId, testAddReqDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // 테스트 목록 조회
    @GetMapping("/projects/{projectId}/tests")
    public ResponseEntity<?> getTestList(@PathVariable final Long projectId) {
        return ResponseEntity.ok().body(testService.getTestList(projectId));
    }

    // 개별 테스트 상세 조회
    @GetMapping("/tests/{testId}")
    public ResponseEntity<?> getTestDetail(@PathVariable final Long testId) {
        return ResponseEntity.ok().body(testService.getTestDetail(testId));
    }

    // 테스트 수정
    @PatchMapping("/tests/{testId}")
    public ResponseEntity<?> motifyTest(@PathVariable final Long testId, @RequestBody @Valid TestUpdateReqDto testUpdateReqDto) {
        testService.motifyTest(testId, testUpdateReqDto);
        return ResponseEntity.ok().build();
    }

    // 테스트 삭제
    @DeleteMapping("/tests/{testId}")
    public ResponseEntity<?> deleteTest(@PathVariable final Long testId) {
        testService.deleteTest(testId);
        return ResponseEntity.ok().build();
    }
}
