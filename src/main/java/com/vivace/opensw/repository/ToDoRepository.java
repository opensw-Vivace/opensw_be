package com.vivace.opensw.repository;

import com.vivace.opensw.entity.ToDo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ToDoRepository extends JpaRepository<ToDo,Long> {


}
