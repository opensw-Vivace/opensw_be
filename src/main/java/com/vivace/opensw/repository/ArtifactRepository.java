package com.vivace.opensw.repository;

import com.vivace.opensw.entity.Artifact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


public interface ArtifactRepository extends JpaRepository<Artifact, Long> {
    public Optional<List<Artifact>> findByProjectId(Long id); //되는지 테스트 해봐야 함.
}
