package com.vivace.opensw.repository;

import com.vivace.opensw.entity.NecessaryArtifactType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NecessaryArtifactTypeRepository extends JpaRepository<NecessaryArtifactType, Long> {
    public void deleteByProjectIdAndArtifactTypeId(Long project_id, Long artifactType_id);
}
