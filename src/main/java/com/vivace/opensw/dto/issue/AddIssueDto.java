package com.vivace.opensw.dto.issue;

import com.vivace.opensw.entity.Issue;
import com.vivace.opensw.model.DocsStatus;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class AddIssueDto {
  private String title;
  private String content;
  private Long projectId;
  private DocsStatus status;

}
