package com.vivace.opensw.service;


import com.vivace.opensw.dto.todo.AddToDo;
import com.vivace.opensw.dto.todo.UpdateToDo;
import com.vivace.opensw.dto.todo.ToDoList;
import com.vivace.opensw.entity.Member;
import com.vivace.opensw.entity.Project;
import com.vivace.opensw.entity.ToDo;
import com.vivace.opensw.repository.MemberRepository;
import com.vivace.opensw.repository.ProjectRepository;
import com.vivace.opensw.repository.ToDoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ToDoService {
  private final ToDoRepository toDoRepository;
  private final ProjectRepository projectRepository;
  private final MemberRepository memberRepository;
  private final MemberService memberService;
  public ToDo save(AddToDo addToDo) {
    System.out.println("Looking for project with ID: " + addToDo.getProjectId());//생성시 프로젝트 저장
    Project project=projectRepository.findById(addToDo.getProjectId()).
        orElseThrow(()->new IllegalArgumentException("cannot found"));

    Member member=memberRepository.findById(memberService.getCurrentMember().getId())
        .orElseThrow(()->new IllegalArgumentException("cannot find"));
    ToDo todo=new ToDo().builder()
        .title(addToDo.getTitle())
        .content(addToDo.getContent())
        .status(addToDo.getStatus())
        .project(project)
        .member(member)
        .build();
    return toDoRepository.save(todo);
  }
  public ToDoList getToDoByTodoId(Long todoId){
    ToDo toDo=toDoRepository.findById(todoId).orElseThrow(()->new IllegalArgumentException("there isn't todo"));
    ToDoList toDoList;
    toDoList=new ToDoList().builder()
        .title(toDo.getTitle())
        .content(toDo.getContent())
        .status(toDo.getStatus())
        .build();
    return toDoList;
  }
  public List<ToDoList> getToDosByProjectId(Long projectId){
   List<ToDo> toDoList=toDoRepository.findByProjectId(projectId).stream().toList();
   ToDoList todo;
   List<ToDoList> todoDtoList=new ArrayList<>();
    Member member=memberRepository.findById(memberService.getCurrentMember().getId())
        .orElseThrow(()->new IllegalArgumentException("cannot find"));
   for(ToDo Todo:toDoList){
      todo=new ToDoList().builder()
          .title(Todo.getTitle())
          .status(Todo.getStatus())
          .ProjectId(Todo.getProject().getId())
          .content(Todo.getContent())
          .build();
     todoDtoList.add(todo);
   }
   return todoDtoList;

  }
  public List<ToDoList> getMyToDosByProjectId(Long projectId,Long memberId){
    List<ToDo> toDoList = toDoRepository.findByProjectIdAndMemberId(projectId, memberId);
    List<ToDoList> todoDtoList=new ArrayList<>();

    for(ToDo todo:toDoList){
      ToDoList todoDto=ToDoList.builder().
          title(todo.getTitle()).
          status(todo.getStatus()).
          ProjectId(todo.getProject().getId())
          .content(todo.getContent())
          .build();
      todoDtoList.add(todoDto);

    }
    return todoDtoList;

  }

  public List<ToDo> findAll(){
    return toDoRepository.findAll();
  }
  @Transactional
  public ToDo update(Long id, UpdateToDo updateToDo){
    Project project=projectRepository.findById(updateToDo.getProjectId())
        .orElseThrow(()->new IllegalArgumentException("cannot find"));

    Member member=memberRepository.findById(memberService.getCurrentMember().getId())
        .orElseThrow(()->new IllegalArgumentException("cannot find"));
   ToDo todo=toDoRepository.findById(id).orElseThrow(()->new IllegalArgumentException());
   todo.update(updateToDo.getTitle(), updateToDo.getContent(), updateToDo.getStatus());

    return toDoRepository.save(todo);
  }

}
