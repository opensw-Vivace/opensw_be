package com.vivace.opensw.dto.todo;

import com.vivace.opensw.entity.ToDo;
import com.vivace.opensw.model.DocsStatus;

public record ToDoList(

    Long ProjectId,
    String title,
    String content,
    DocsStatus status

) {
  public static ToDoList from(ToDo todo){
    return new ToDoList(

        todo.getProject().getId(),
        todo.getTitle(),
        todo.getContent(),
        todo.getStatus()
    );
  }
}
