package com.vivace.opensw.repository;

import com.vivace.opensw.entity.Member;
import com.vivace.opensw.entity.Position;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PositionRepository extends JpaRepository<Position, Long> {
    public Optional<List<Position>> findAllByMemberId(Long member_id);
    public Optional<List<Position>> findAllByMember(Member member);
    boolean existsByMemberIdAndParticipate_ProjectId(Long memberId, Long projectId);
}
