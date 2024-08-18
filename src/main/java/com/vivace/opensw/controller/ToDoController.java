package com.vivace.opensw.controller;

import com.vivace.opensw.dto.todo.AddToDo;
import com.vivace.opensw.dto.todo.UpdateToDo;
import com.vivace.opensw.dto.todo.ToDoList;
import com.vivace.opensw.entity.ToDo;
import com.vivace.opensw.service.ToDoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ToDoController {
  private final ToDoService toDoService;
  @PostMapping("/projects/{projectId}/todos")
  public ResponseEntity<ToDo> addToDo(@RequestBody AddToDo addToDo, @PathVariable("projectId") Long id){
    addToDo.setProjectId(id);

    ToDo toDo=toDoService.save(addToDo);
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(toDo);
  }
  @PatchMapping("/todos/{todoId}")
  public ResponseEntity<ToDo> modifyToDo(@RequestBody UpdateToDo updateToDo, @PathVariable("todoId") Long id){
    ToDo todo= toDoService.update(id,updateToDo);
    return ResponseEntity.ok().body(todo);
  }
  @GetMapping("/projects/{projectId}/todos")
  public ResponseEntity<List<ToDoList>> getToDoList(@PathVariable("projectId") Long projectid){
    List<ToDoList> todos=toDoService.getToDosByProjectId(projectid);
    return ResponseEntity.ok().body(todos);
  }
}
