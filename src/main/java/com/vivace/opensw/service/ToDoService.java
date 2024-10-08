package com.vivace.opensw.service;


import com.vivace.opensw.dto.todo.AddToDo;
import com.vivace.opensw.dto.todo.UpdateToDo;
import com.vivace.opensw.dto.todo.ToDoList;
import com.vivace.opensw.entity.Member;
import com.vivace.opensw.entity.Project;
import com.vivace.opensw.entity.ToDo;
import com.vivace.opensw.global.exception.CustomException;
import com.vivace.opensw.global.exception.ErrorCode;
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
  public ToDo save(AddToDo addToDo) {//특정 프로젝트 안에서 todo 생성
    System.out.println("Looking for project with ID: " + addToDo.getProjectId());//생성시 프로젝트 저장
    Project project=projectRepository.findById(addToDo.getProjectId())
        .orElseThrow(() -> new CustomException(ErrorCode.PROJECT_NOT_FOUND));

    Member member=memberService.getCurrentMember();
    ToDo todo=new ToDo().builder()
        .title(addToDo.getTitle())
        .content(addToDo.getContent())
        .status(addToDo.getStatus())
        .project(project)
        .member(member)
        .build();
    return toDoRepository.save(todo);
  }

  public ToDoList getToDoByMemberId(Long todoId){//사용안함
    Member member=memberService.getCurrentMember();
    ToDo toDo=toDoRepository.findByMemberId(member.getId()).get();
    ToDoList toDoList;

    toDoList=new ToDoList().builder()
        .title(toDo.getTitle())
        .content(toDo.getContent())
        .status(toDo.getStatus())
        .build();
    return toDoList;
  }
  public List<ToDoList> getToDosByProjectId(Long projectId){//사용안함
   List<ToDo> toDoList=toDoRepository.findByProjectId(projectId).stream().toList();
   ToDoList todo;
   List<ToDoList> todoDtoList=new ArrayList<>();
    Member member=memberService.getCurrentMember();
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



  @Transactional
  public ToDo update(Long id, UpdateToDo updateToDo) {//할 일 수정 메서드


    ToDo todo = toDoRepository.findById(id)
        .orElseThrow(() -> new CustomException(ErrorCode.TODO_NOT_FOUND));

    todo.update(updateToDo.getTitle(), updateToDo.getContent(), updateToDo.getStatus());

    return toDoRepository.save(todo);
  }
  @Transactional
  public List<ToDoList> getToDosByProjectIdAndMember(Long projectId) {//로그인 한 사람의 특정 프로젝트 안에서 할 일 조회
    Member member = memberService.getCurrentMember();
    System.out.println(member.getId());

    List<ToDo> toDoList = toDoRepository.findByProjectIdAndMemberId(projectId, member.getId());


    return toDoList.stream()
        .map(toDo -> ToDoList.builder()
            .title(toDo.getTitle())
            .content(toDo.getContent())
            .status(toDo.getStatus())
            .ProjectId(projectId)
            .build())
        .collect(Collectors.toList());
  }




}
