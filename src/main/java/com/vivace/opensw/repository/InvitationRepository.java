package com.vivace.opensw.repository;

import com.vivace.opensw.entity.Invitation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InvitationRepository extends JpaRepository<Invitation, Long> {
    public Optional<List<Invitation>> findByReceiverId(Long id);
}
