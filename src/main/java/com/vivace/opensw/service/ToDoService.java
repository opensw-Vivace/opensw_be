package com.vivace.opensw.service;


import com.vivace.opensw.dto.todo.AddToDo;
import com.vivace.opensw.dto.todo.UpdateToDo;
import com.vivace.opensw.dto.todo.ToDoList;
import com.vivace.opensw.entity.Project;
import com.vivace.opensw.entity.ToDo;
import com.vivace.opensw.repository.ProjectRepository;
import com.vivace.opensw.repository.ToDoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ToDoService {
  private final ToDoRepository toDoRepository;
  private final ProjectRepository projectRepository;
  public ToDo save(AddToDo addToDo) {//생성시 프로젝트 저장
    Project project=projectRepository.findById(addToDo.getProjectId())
        .orElseThrow(()->new IllegalArgumentException("cannot find"));
    ToDo todo=addToDo.toEntity(project);
    return toDoRepository.save(todo);

  }
  public List<ToDoList> getToDosByProjectId(Long projectId){
    List<ToDo> todos=toDoRepository.findByProjectId(projectId);
    return todos.stream().map(ToDoList::from)
        .collect(Collectors.toList());
  }

  public List<ToDo> findAll(){
    return toDoRepository.findAll();
  }
  @Transactional
  public ToDo update(Long id, UpdateToDo updateToDo){
    ToDo todo=toDoRepository.findById(id).orElseThrow(()->new IllegalArgumentException("not found"+id));
    todo.update(updateToDo.getTitle(), updateToDo.getContent(), updateToDo.getStatus());
    return todo;
  }

}
