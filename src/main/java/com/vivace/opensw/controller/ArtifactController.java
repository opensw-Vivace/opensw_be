package com.vivace.opensw.controller;

import com.vivace.opensw.dto.artifact.ArtifactReqDto;
import com.vivace.opensw.dto.artifact.ArtifactResDto;
import com.vivace.opensw.service.ArtifactService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ArtifactController {

    private final ArtifactService artifactService;


    /**
     * 산출물 작성해서 db에 저장
     */
    @PostMapping("/artifacts")
    public ResponseEntity<?> save(@RequestBody ArtifactReqDto artifactReqDto){
        artifactService.save(artifactReqDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(null); //body에 무언가 넣어야 하는가?
    }

    /**
     * 프로젝트 내 모든 산출물 조회
     */
    @GetMapping("/projects/{projectId}/artifacts")
    public ResponseEntity<List<ArtifactResDto>> findByProjectId(@PathVariable Long projectId){
        List<ArtifactResDto> artifactResDtoList= artifactService.findByProjectId(projectId);
        return ResponseEntity.status(HttpStatus.OK).body(artifactResDtoList);
    }

    /**
     * 특정 산출물의 상태 변경
     */
    @PatchMapping("/artifacts/{artifactId}/status")
    public ResponseEntity<Boolean> updateStatus(@PathVariable Long artifactId, @RequestParam String status){
        Boolean result=artifactService.updateStatus(artifactId, status);
        return ResponseEntity.status(HttpStatus.OK).body(result); //상태코드 update시에는 어떻게?
    }
}
