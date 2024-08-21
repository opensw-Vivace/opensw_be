package com.vivace.opensw.repository;

import com.vivace.opensw.entity.Position;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PositionRepository extends JpaRepository<Position, Long> {
    boolean existsByMemberIdAndParticipate_ProjectId(Long memberId, Long projectId);
}
