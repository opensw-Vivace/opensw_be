package com.vivace.opensw.service;

import com.vivace.opensw.dto.issue.AddIssueDto;
import com.vivace.opensw.dto.issue.IssueListDto;
import com.vivace.opensw.dto.issue.UpdateIssueDto;
import com.vivace.opensw.entity.Issue;
import com.vivace.opensw.entity.Member;
import com.vivace.opensw.entity.Project;
import com.vivace.opensw.entity.ToDo;
import com.vivace.opensw.global.exception.CustomException;
import com.vivace.opensw.global.exception.ErrorCode;
import com.vivace.opensw.repository.IssueRepository;
import com.vivace.opensw.repository.MemberRepository;
import com.vivace.opensw.repository.ProjectRepository;
import com.vivace.opensw.repository.ToDoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IssueService {
  private final IssueRepository issueRepository;
  private final ProjectRepository projectRepository;
  private final MemberRepository memberRepository;
  private final MemberService memberService;

  public Issue save(AddIssueDto addIssueDto){
    Project project=projectRepository.findById(addIssueDto.getProjectId())
        .orElseThrow(()->new CustomException(ErrorCode.ISSUE_NOT_FOUND));
    Member member=memberService.getCurrentMember();
    Issue issue=new Issue().builder().
        title(addIssueDto.getTitle())
        .content(addIssueDto.getContent())
        .status(addIssueDto.getStatus())
        .name(member.getName())
        .project(project)
        .member(member)
        .build();
    return issueRepository.save(issue);
  }
  public List<IssueListDto> getIssuesByProjectId(Long projectId){
    Member member=memberService.getCurrentMember();
    List<Issue> issueList=issueRepository.findByProjectIdAndMemberId(projectId, member.getId());

    return issueList.stream()
        .map(issue -> IssueListDto.builder()
            .title(issue.getTitle())
            .content(issue.getContent())
            .status(issue.getStatus())
            .name(issue.getName())
            .created_at(issue.getCreatedAt())
            .build())
        .collect(Collectors.toList());

  }
  public IssueListDto getIssuesDetail(Long issueId){
    Issue issue=issueRepository.findById(issueId).orElseThrow(()->new CustomException(ErrorCode.ISSUE_NOT_FOUND));
    IssueListDto issueListDto=new IssueListDto()
        .builder()
        .title(issue.getTitle())
        .status(issue.getStatus())
        .name(issue.getName())
        .created_at(issue.getCreatedAt())
        .content(issue.getContent()).build();
    return issueListDto;

  }
  @Transactional
  public Issue update(Long id, UpdateIssueDto issueDto){
    Issue issue=issueRepository.findById(id).orElseThrow(()->new CustomException(ErrorCode.ISSUE_NOT_FOUND));
    issue.update(issueDto.getTitle(),issueDto.getStatus(),issueDto.getContent());
    return issueRepository.save(issue);
  }




}
