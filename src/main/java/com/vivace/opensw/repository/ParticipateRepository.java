package com.vivace.opensw.repository;

import com.vivace.opensw.entity.Member;
import com.vivace.opensw.entity.Participate;
import com.vivace.opensw.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ParticipateRepository extends JpaRepository<Participate, Long> {
    boolean existsByProjectIdAndMemberId(Long projectId, Long memberId);
    Optional<Participate> findByProjectAndMember(Project project, Member member);
    List<Participate> findAllByProject(Project project);
    List<Participate> findAllByMember(Member member);
}
