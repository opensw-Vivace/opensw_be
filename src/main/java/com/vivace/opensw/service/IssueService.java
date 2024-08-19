package com.vivace.opensw.service;

import com.vivace.opensw.dto.issue.AddIssueDto;
import com.vivace.opensw.dto.issue.IssueListDto;
import com.vivace.opensw.entity.Issue;
import com.vivace.opensw.entity.Member;
import com.vivace.opensw.entity.Project;
import com.vivace.opensw.repository.IssueRepository;
import com.vivace.opensw.repository.MemberRepository;
import com.vivace.opensw.repository.ProjectRepository;
import com.vivace.opensw.repository.ToDoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class IssueService {
  private final IssueRepository issueRepository;
  private final ProjectRepository projectRepository;
  private final MemberRepository memberRepository;
  private final MemberService memberService;
  public Issue save(AddIssueDto addIssueDto){
    Project project=projectRepository.findById(addIssueDto.getProjectId())
        .orElseThrow(()->new IllegalArgumentException("cannot found"));
    Member member=memberRepository.findById(memberService.getCurrentMember().getId())
        .orElseThrow(()->new IllegalArgumentException("cannot found"));
    Issue issue=new Issue().builder().
        title(addIssueDto.getTitle())
        .content(addIssueDto.getContent())
        .status(addIssueDto.getStatus())
        .project(project)
        .member(member)
        .build();
    return issueRepository.save(issue);
  }
  public List<IssueListDto> getIssuesByProjectId(Long projectId){
    List<Issue> issueListDtos=issueRepository.findByProjectId(projectId).get();
    IssueListDto issue;
    List<IssueListDto> issueDtoList=new ArrayList<>();
    for(Issue Issue:issueListDtos){
      issue=new IssueListDto().builder().
      title(Issue.getTitle()).
          content(Issue.getContent()).
          status(Issue.getStatus()).
          projectId(Issue.getProject().getId()).
          build();
      issueDtoList.add(issue);
    }
    return  issueDtoList;
  }

}
