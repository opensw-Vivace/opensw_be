package com.vivace.opensw.repository;

import com.vivace.opensw.entity.Invitation;
import com.vivace.opensw.entity.ToDo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ToDoRepository extends JpaRepository<ToDo, Long> {

  public Optional<List<ToDo>> findByProjectId(Long id);
  public Optional<List<ToDo>> findByProjectIdAndMemberId(Long projectId,Long memberId);
}
