package com.vivace.opensw.repository;

import com.vivace.opensw.entity.ArtifactType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtifactTypeRepository extends JpaRepository<ArtifactType,Long> {
}
