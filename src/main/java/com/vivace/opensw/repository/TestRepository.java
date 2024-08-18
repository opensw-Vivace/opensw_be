package com.vivace.opensw.repository;

import com.vivace.opensw.entity.Test;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepository extends JpaRepository<Test, Long> {
}
