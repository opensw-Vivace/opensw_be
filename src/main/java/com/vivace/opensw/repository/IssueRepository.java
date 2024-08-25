package com.vivace.opensw.repository;

import com.vivace.opensw.dto.issue.IssueListDto;
import com.vivace.opensw.entity.Issue;
import com.vivace.opensw.entity.ToDo;
import com.vivace.opensw.model.ArtifactStatus;
import com.vivace.opensw.model.DocsStatus;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IssueRepository extends JpaRepository<Issue, Long> {
  public Optional<List<Issue>> findByProjectId(Long id);
  List<Issue> findByProjectIdAndMemberId(Long projectId, Long memberId);

  public int countByProjectId(Long project_id);
  public int countByProjectIdAndStatus(Long project_id, @NotNull DocsStatus status);
}