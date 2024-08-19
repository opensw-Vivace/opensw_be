package com.vivace.opensw.repository;

import com.vivace.opensw.entity.Issue;
import com.vivace.opensw.entity.ToDo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IssueRepository extends JpaRepository<Issue, Long> {
  public Optional<List<Issue>> findByProjectId(Long id);
}