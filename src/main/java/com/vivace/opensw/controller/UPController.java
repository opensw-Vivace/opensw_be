package com.vivace.opensw.controller;

import com.vivace.opensw.dto.progress.ProgressPercentResDto;
import com.vivace.opensw.model.UPStatus;
import com.vivace.opensw.service.UPService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UPController {

    private final UPService upService;

    /**
     * 현재 unified process 단계 조회
     */
    @GetMapping("/projects/{projectId}/phase")
    public ResponseEntity<UPStatus> getUPStatus(@PathVariable Long projectId){
        UPStatus upStatus=upService.getUPStatus(projectId);
        return ResponseEntity.ok(upStatus);
    }

    /**
     * 다음 phase로 이동
     */
    @PostMapping("/projects/{projectId}/phase")
    public ResponseEntity moveNextPhase(@PathVariable Long projectId){
        upService.moveNextPhase(projectId);
        return ResponseEntity.ok().build();
    }

    /**
     * 다음 iteration으로 이동
     */
    @PostMapping("/projects/{projectId}/iteration")
    public ResponseEntity moveNextIteration(@PathVariable Long projectId){
        upService.moveNextIteration(projectId);
        return ResponseEntity.ok().build();
    }

    /**
     * 전체 진행상황 조회
     */
    @GetMapping("/projects/{projectId}/progress")
    public ResponseEntity<ProgressPercentResDto> getProgressPercent(@PathVariable Long projectId){
        ProgressPercentResDto progressPercentResDto=new ProgressPercentResDto(
                upService.getTotalPercent(projectId),
                upService.getUPPercent(projectId)
        );

        return ResponseEntity.ok(progressPercentResDto);
    }



}
