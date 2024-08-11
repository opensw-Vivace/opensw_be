package com.vivace.opensw.repository;

import com.vivace.opensw.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
