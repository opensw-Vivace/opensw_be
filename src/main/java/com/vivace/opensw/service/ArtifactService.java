package com.vivace.opensw.service;

import com.vivace.opensw.dto.artifact.ArtifactPostDto;
import com.vivace.opensw.entity.Artifact;
import com.vivace.opensw.entity.ArtifactCreator;
import com.vivace.opensw.entity.Img;
import com.vivace.opensw.model.ArtifactStatus;
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
    private final MemberRepository memberRepository;


    /**
     * 산출물 생성해서 db에 저장.
     * 테스트 완료
     */
    public void save(ArtifactPostDto artifactPostDto){
        List<Img> imgList=new ArrayList<>();
        List<ArtifactCreator> creatorList=new ArrayList<>();
        ArtifactCreator artifactCreator;
        Artifact artifact;
        for(String imgPath : artifactPostDto.getImgPathList()){
            imgList.add(imgRepository.findByImgPath(imgPath).get());
        }
        for(Long writerId: artifactPostDto.getWriterIdList()){
            artifactCreator=new ArtifactCreator().builder()
                    .member(memberRepository.findById(writerId).get())
                    .build();

            creatorList.add(artifactCreator);
            /**
             * creator 테이블의 튜블이 생성되기 전에 조회하니 오류가 발생함.
             * try-catch문 활용해서 에러가 발생하지 않으면 creator을 생성하고
             * 에러가 발생하면 트랜젝션으로 전체 취소하도록 로직 설계.
             * @트랜젝션 어노테이션 활용 가능?
             */
        }

        artifact=new Artifact().builder()
                .title(artifactPostDto.getTitle())
                .subtitle(artifactPostDto.getSubtitle())
                .status(artifactPostDto.getStatus())
                .deadline(artifactPostDto.getDeadline())
                .project(projectRepository.findById(artifactPostDto.getProjectId()).get())
                .artifactType(artifactTypeRepository.findById(artifactPostDto.getArtifactTypeId()).get())
                .imgList(imgList)
                .creatorList(creatorList)
                .status(ArtifactStatus.NOT_STARTED)
                .build();

        artifactRepository.save(artifact);

        for(ArtifactCreator creator:creatorList){
            creator.setArtifact(artifact);
            creatorRepository.save(creator);
        }
    }


}
