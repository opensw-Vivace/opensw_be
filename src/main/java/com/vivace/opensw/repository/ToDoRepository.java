package com.vivace.opensw.repository;

import com.vivace.opensw.entity.ToDo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ToDoRepository extends JpaRepository<ToDo, Long> {
  List<ToDo> findByProjectId(Long projectId);//project id를 기반하여 todolist를 조회

}
