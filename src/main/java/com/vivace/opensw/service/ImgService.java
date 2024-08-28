package com.vivace.opensw.service;

import com.vivace.opensw.entity.Img;
import com.vivace.opensw.global.exception.CustomException;
import com.vivace.opensw.global.exception.ErrorCode;
import com.vivace.opensw.repository.ArtifactRepository;
import com.vivace.opensw.repository.ImgRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

@Service
@RequiredArgsConstructor
@Transactional
public class ImgService {

    private final ImgRepository imgRepository;
    private final ArtifactRepository artifactRepository;

    public void save(String imgPath, Long artifactId){
        Img newImg=new Img().builder()
                .imgPath(imgPath)
                .artifact(artifactRepository.findById(artifactId)
                        .orElseThrow(()->new CustomException(ErrorCode.ARTIFACT_NOT_FOUND)))
                .build();

        imgRepository.save(newImg);
    }


}
