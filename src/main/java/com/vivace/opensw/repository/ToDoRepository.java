package com.vivace.opensw.repository;

import com.vivace.opensw.entity.Invitation;
import com.vivace.opensw.entity.ToDo;
import com.vivace.opensw.model.ArtifactStatus;
import com.vivace.opensw.model.DocsStatus;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ToDoRepository extends JpaRepository<ToDo, Long> {

  List<ToDo> findByProjectId(Long id);
  Optional<ToDo> findByMemberId(Long id);
  List<ToDo> findByProjectIdAndMemberId(Long projectId,Long memberId);

  public int countByProjectId(Long project_id);
  public int countByProjectIdAndStatus(Long project_id, @NotNull DocsStatus status);

}
