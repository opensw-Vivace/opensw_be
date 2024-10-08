package com.vivace.opensw.controller;

import com.vivace.opensw.dto.issue.AddIssueDto;
import com.vivace.opensw.dto.issue.IssueListDto;
import com.vivace.opensw.dto.issue.UpdateIssueDto;
import com.vivace.opensw.dto.todo.AddToDo;
import com.vivace.opensw.dto.todo.ToDoList;
import com.vivace.opensw.dto.todo.UpdateToDo;
import com.vivace.opensw.entity.Issue;
import com.vivace.opensw.entity.ToDo;
import com.vivace.opensw.global.exception.CustomException;
import com.vivace.opensw.global.exception.ErrorCode;
import com.vivace.opensw.repository.IssueRepository;
import com.vivace.opensw.service.IssueService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class IssueController {
  private final IssueService issueService;
  private final IssueRepository issueRepository;
  @PostMapping("/projects/{projectId}/issues")
  public ResponseEntity<Issue> addIssue(@RequestBody AddIssueDto addIssueDto, @PathVariable("projectId") Long id){
    Issue issue=issueService.save(addIssueDto);
    return ResponseEntity.ok().body(issue);
  }



  @GetMapping("/projects/{projectId}/issues")
  public ResponseEntity<List<IssueListDto>> getIssueList(@PathVariable("projectId") Long projectid){
   List<IssueListDto> issueListDtos=issueService.getIssuesByProjectId(projectid);
    return ResponseEntity.ok().body(issueListDtos);
  }


  @GetMapping("/issues/{issueId}")
  public ResponseEntity<IssueListDto> getIssueDetail(@PathVariable("issueId") Long id){
    IssueListDto issueListDto=issueService.getIssuesDetail(id);
    return ResponseEntity.ok().body(issueListDto);
  }
  @PatchMapping("/issues/{issueId}")
  public ResponseEntity<Issue> modifyIssue(@RequestBody final UpdateIssueDto updateIssueDto, @PathVariable("issueId")final Long id){
    Issue issue= issueService.update(id,updateIssueDto);
    return ResponseEntity.ok().body(issue);
  }




}
