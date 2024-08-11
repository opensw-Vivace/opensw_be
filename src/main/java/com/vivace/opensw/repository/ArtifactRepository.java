package com.vivace.opensw.repository;

import com.vivace.opensw.entity.Artifact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtifactRepository extends JpaRepository<Artifact, Long> {
}
