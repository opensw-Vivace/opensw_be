package com.vivace.opensw.repository;

import com.vivace.opensw.entity.Artifact;
import com.vivace.opensw.model.ArtifactStatus;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


public interface ArtifactRepository extends JpaRepository<Artifact, Long> {
    public Optional<List<Artifact>> findByProjectId(Long id); //되는지 테스트 해봐야 함. 되는듯
    public int countByProjectId(Long project_id);
    public int countByProjectIdAndStatus(Long project_id, @NotNull ArtifactStatus status);

}
