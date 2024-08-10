package com.vivace.opensw.controller;

import com.vivace.opensw.dto.ArtifactDto;
import com.vivace.opensw.service.ArtifactService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class ArtifactController {

    private final ArtifactService artifactService;


    /**
     * 산출물 작성해서 db에 저장
     */
    @PostMapping("/artifacts")
    public void save(@RequestBody ArtifactDto artifactDto){
        artifactService.save(artifactDto);
    }
}
