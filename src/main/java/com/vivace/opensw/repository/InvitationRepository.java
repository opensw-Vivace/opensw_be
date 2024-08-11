package com.vivace.opensw.repository;

import com.vivace.opensw.entity.Invitation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvitationRepository extends JpaRepository<Invitation, Long> {
}
