package com.vivace.opensw.controller;

import com.vivace.opensw.dto.necessary.NecessaryReqDto;
import com.vivace.opensw.entity.NecessaryArtifactType;
import com.vivace.opensw.service.NecessaryArtifactService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class NecessaryArtifactTypeController {

    private final NecessaryArtifactService necessaryArtifactService;

    /**
     * 어떤 프로젝트의 필수 산출물 설정(필수/ 비필수 설정)
     */
    @PostMapping("/projects/{projectId}/necessary")
    public ResponseEntity<?> setNecessary(@PathVariable Long projectId, @RequestBody NecessaryReqDto necessaryReqDto){
        HttpStatus httpStatus= necessaryArtifactService.setNecessaryArtifactType(projectId, necessaryReqDto);
        return ResponseEntity.status(httpStatus).build();
    }
}
