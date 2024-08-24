package com.vivace.opensw.dto.issue;

import com.vivace.opensw.model.DocsStatus;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class UpdateIssueDto {
  private String title;
  private DocsStatus status;
  private String content;

}
