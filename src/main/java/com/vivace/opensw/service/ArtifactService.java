package com.vivace.opensw.service;

import com.vivace.opensw.dto.ArtifactDto;
import com.vivace.opensw.entity.Artifact;
import com.vivace.opensw.entity.Creator;
import com.vivace.opensw.entity.Img;
import com.vivace.opensw.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ArtifactService {

    private final ArtifactRepository artifactRepository;
    private final ProjectRepository projectRepository;
    private final ArtifactTypeRepository artifactTypeRepository;
    private final ImgRepository imgRepository;
    private final CreatorRepository creatorRepository;


    /**
     * 산출물 생성해서 db에 저장.
     */
    public void save(ArtifactDto artifactDto){
        List<Img> imgList=new ArrayList<>();
        List<Creator> creatorList=new ArrayList<>();
        for(String imgPath : artifactDto.getImgPathList()){
            imgList.add(imgRepository.findByPath(imgPath).get());
        }
        for(Long creatorId: artifactDto.getCreatorId()){
            creatorList.add(creatorRepository.findById(creatorId).get());
        }

        Artifact artifact=new Artifact().builder()
                .id(artifactDto.getId())
                .title(artifactDto.getTitle())
                .subtitle(artifactDto.getSubtitle())
                .status(artifactDto.getStatus())
                .deadline(artifactDto.getDeadline())
                .createdAt(artifactDto.getCreatedAt())
                .updatedAt(artifactDto.getUpdatedAt())
                .project(projectRepository.findById(artifactDto.getProjectId()).get())
                .artifactType(artifactTypeRepository.findById(artifactDto.getArtifactTypeId()).get())
                .imgList(imgList)
                .creatorList(creatorList)
                .createdAt(artifactDto.getCreatedAt())
                .updatedAt(artifactDto.getUpdatedAt())
                .build();

        artifactRepository.save(artifact);
    }


}
