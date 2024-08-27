package com.vivace.opensw.diagram;

import com.vivace.opensw.entity.ArtifactType;
import com.vivace.opensw.repository.ArtifactTypeRepository;
import org.aspectj.lang.annotation.RequiredTypes;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class insertDiagram {


    private final ArtifactTypeRepository artifactTypeRepository;

    @Autowired
    public insertDiagram(ArtifactTypeRepository artifactTypeRepository){
        this.artifactTypeRepository=artifactTypeRepository;
    }
    /**
     * 산출물 종류 저장 DB에 값 삽입
     */
    @Test
    public void 산출물종류삽입(){
        artifactTypeRepository.save(new ArtifactType(1L,"SRS",null,null));
        artifactTypeRepository.save(new ArtifactType(2L,"Flow Chart",null,null));
        artifactTypeRepository.save(new ArtifactType(3L,"Draft",null,null));
        artifactTypeRepository.save(new ArtifactType(4L,"Final Ver.",null,null));
        artifactTypeRepository.save(new ArtifactType(5L,"ERD",null,null));
        artifactTypeRepository.save(new ArtifactType(6L,"Api Specification",null,null));
        artifactTypeRepository.save(new ArtifactType(7L,"QA",null,null));
    }

}
