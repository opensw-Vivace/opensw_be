package com.vivace.opensw.repository;

import com.vivace.opensw.entity.Invite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InviteRepository extends JpaRepository<Invite, Long> {
    public Optional<List<Invite>> findByReceiverId(Long id); //기능 체크 필요.
}
