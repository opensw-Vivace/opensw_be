package com.vivace.opensw.controller;

import com.vivace.opensw.dto.todo.AddToDo;
import com.vivace.opensw.dto.todo.UpdateToDo;
import com.vivace.opensw.dto.todo.ToDoList;
import com.vivace.opensw.entity.ToDo;
import com.vivace.opensw.service.MemberService;
import com.vivace.opensw.service.ProjectService;
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
  private final ProjectService projectService;
  private final MemberService memberService;
  @PostMapping("/projects/{projectId}/todos")
  public ResponseEntity<ToDo> addToDo(@RequestBody AddToDo addToDo, @PathVariable("projectId") Long id){
    ToDo todo=toDoService.save(addToDo);
    return ResponseEntity.ok().body(todo);
  }
  @PatchMapping("/todos/{todoId}")
  public ResponseEntity<ToDo> modifyToDo(@RequestBody UpdateToDo updateToDo, @PathVariable("todoId") Long id){
    ToDo todo= toDoService.update(id,updateToDo);
    return ResponseEntity.ok().body(todo);
  }
//  @GetMapping("/todos/{todoId}")
//  public ResponseEntity<List<ToDoList>> getMyTodobyId(@PathVariable("todoId") Long id) {
//    List<ToDoList> todoList = toDoService.getToDosByProjectIdAndMember(id);
//    return ResponseEntity.ok().body(todoList);
//  }

  @GetMapping("/projects/{projectId}/todos")
  public ResponseEntity<List<ToDoList>> getToDoList(@PathVariable("projectId") Long projectid){
    List<ToDoList> todos=toDoService.getToDosByProjectIdAndMember(projectid);
    return ResponseEntity.ok().body(todos);
  }

}
