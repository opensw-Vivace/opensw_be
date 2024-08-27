package com.vivace.opensw.repository;

import com.vivace.opensw.entity.NecessaryArtifactType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NecessaryArtifactTypeRepository extends JpaRepository<NecessaryArtifactType, Long> {
    public void deleteByProjectIdAndArtifactTypeId(Long project_id, Long artifactType_id);
    public List<NecessaryArtifactType> findByProjectId(Long project_id);
}
