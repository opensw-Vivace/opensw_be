package com.vivace.opensw.dto.issue;

import com.vivace.opensw.model.DocsStatus;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class IssueListDto {
  private String title;
  private String name;
  private String content;
  private DocsStatus status;
  private LocalDateTime created_at;
}
