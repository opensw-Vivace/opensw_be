package com.vivace.opensw.service;

import com.vivace.opensw.dto.artifact.ArtifactReqDto;
import com.vivace.opensw.dto.artifact.ArtifactResDto;
import com.vivace.opensw.entity.Artifact;
import com.vivace.opensw.entity.ArtifactCreator;
import com.vivace.opensw.entity.Img;
import com.vivace.opensw.entity.Member;
import com.vivace.opensw.global.exception.CustomException;
import com.vivace.opensw.global.exception.ErrorCode;
import com.vivace.opensw.model.ArtifactStatus;
import com.vivace.opensw.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public void save(ArtifactReqDto artifactReqDto){
        List<Img> imgList=new ArrayList<>();
        List<ArtifactCreator> creatorList=new ArrayList<>();
        ArtifactCreator artifactCreator;
        Artifact artifact;



        for(Long writerId: artifactReqDto.getWriterIdList()){
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
                .title(artifactReqDto.getTitle())
                .subtitle(artifactReqDto.getSubtitle())
                .status(artifactReqDto.getStatus())
                .deadline(artifactReqDto.getDeadline())
                .project(projectRepository.findById(artifactReqDto.getProjectId()).get())
                .artifactType(artifactTypeRepository.findById(artifactReqDto.getArtifactTypeId()).get())
                .creatorList(creatorList)
                .status(ArtifactStatus.NOT_STARTED)
                .build();

        for(String imgPath : artifactReqDto.getImgPathList()){
            imgList.add(new Img().builder().imgPath(imgPath).artifact(artifact).build());
        }

        artifact.updateImgList(imgList);
        artifactRepository.save(artifact);

        for(ArtifactCreator creator:creatorList){
            creator.setArtifact(artifact);
            creatorRepository.save(creator);
        }
    }



    /**
     * 특정 프로젝트에 속한 산출물들을 리턴해줌.
     */
    public List<ArtifactResDto> findByProjectId(Long projectId){
        List<ArtifactResDto> artifactResDtoList=new ArrayList<>();
        List<Artifact> artifactList=artifactRepository.findByProjectId(projectId).get(); //검증 필요
        ArtifactResDto artifactResDto;
        for(Artifact artifact:artifactList){
            artifactResDto=toResDto(artifact);

            artifactResDtoList.add(artifactResDto);
        }

        return artifactResDtoList;
    }


    /**
     * 산출물의 상태 변경 메소드.
     * 변경 성공 시 true, 실패 시 false 리턴.
     */
    public boolean updateStatus(Long artifactId, String status){
        ArtifactStatus artifactStatus= Enum.valueOf(ArtifactStatus.class, status); //enum 타입으로 형변환
        Optional<Artifact> artifactOptional=artifactRepository.findById(artifactId);

        if(artifactOptional.isEmpty()) return false;
        Artifact artifact=artifactOptional.get();
        artifact.updateStatus(artifactStatus); //찾은 산출물의 상태 변경
        artifactRepository.save(artifact); //변경해서 db에 저장
        return artifactRepository.findById(artifactId).get().getStatus().equals(artifactStatus); //정상적으로 변경되었다면 true
        /**
         * return부분에서 db를 한번 더 조회해서 성능의 문제가 생길 수 있음.
         * 느려진다면 삭제하거나, 다른 방안을 모색해야 할 듯.
         */
    }

    /**
     * 산출물 상세정보
     */
    public ArtifactResDto getDetailsById(Long id){
        Artifact artifact=artifactRepository.findById(id)
                .orElseThrow(()->new CustomException(ErrorCode.ARTIFACT_NOT_FOUND));

        return toResDto(artifact);
    }



    /**
     * entity->resDto
     */
    public ArtifactResDto toResDto(Artifact artifact){
        ArtifactResDto artifactResDto=new ArtifactResDto().builder()
                .id(artifact.getId())
                .title(artifact.getTitle())
                .subtitle(artifact.getSubtitle())
                .status(artifact.getStatus())
                .deadline(artifact.getDeadline())
                .projectId(artifact.getProject().getId())
                .artifactTypeId(artifact.getArtifactType().getId())
                .imgPathList(artifact.getImgList().stream().map(Img::getImgPath).toList())
                .artifactCreatorIdList(artifact.getCreatorList().stream().map(ArtifactCreator::getId).toList()) //creator 엔티티의 id
                .memberIdList(artifact.getCreatorList()
                        .stream().map(ArtifactCreator::getMember).toList()
                        .stream().map(Member::getId).toList()) //작성자의 id
                .build();

        return artifactResDto;
    }



}
