package com.vivace.opensw.repository;

import com.vivace.opensw.entity.ArtifactCreator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreatorRepository extends JpaRepository<ArtifactCreator, Long> {
}
