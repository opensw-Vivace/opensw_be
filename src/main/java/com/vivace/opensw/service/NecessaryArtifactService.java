package com.vivace.opensw.service;

import com.vivace.opensw.dto.necessary.NecessaryReqDto;
import com.vivace.opensw.entity.NecessaryArtifactType;
import com.vivace.opensw.global.exception.CustomException;
import com.vivace.opensw.global.exception.ErrorCode;
import com.vivace.opensw.repository.ArtifactTypeRepository;
import com.vivace.opensw.repository.NecessaryArtifactTypeRepository;
import com.vivace.opensw.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class NecessaryArtifactService {

    private final NecessaryArtifactTypeRepository necessaryArtifactTypeRepository;
    private final ArtifactTypeRepository artifactTypeRepository;
    private final ProjectRepository projectRepository;

    /**
     * 필수 산출물 설정
     */
    @Transactional
    public HttpStatus setNecessaryArtifactType(Long projectId, NecessaryReqDto necessaryReqDto){

        //필수로 설정할 때 ->DB에 저장
        if(necessaryReqDto.isNecessary()) {
            //필수 산출물 엔티티 생성
            NecessaryArtifactType necessaryArtifactType = new NecessaryArtifactType().builder()
                    .project(projectRepository.findById(projectId)
                            .orElseThrow(() -> new CustomException(ErrorCode.PROJECT_NOT_FOUND)))
                    .artifactType(artifactTypeRepository.findById(necessaryReqDto.getArtifactTypeId())
                            .orElseThrow(() -> new CustomException(ErrorCode.ARTIFACT_TYPE_NOT_FOUND)))
                    .build();

            //생성된 엔티티 저장
            necessaryArtifactTypeRepository.save(necessaryArtifactType);

            //코드 반환
            return HttpStatus.CREATED;
        }
        //필수가 아니도록 설정할 때 -> DB에서 삭제
        else{
            necessaryArtifactTypeRepository.deleteByProjectIdAndArtifactTypeId(
                    projectId,
                    necessaryReqDto.getArtifactTypeId()
            );

            return HttpStatus.OK;
        }

    }
}
