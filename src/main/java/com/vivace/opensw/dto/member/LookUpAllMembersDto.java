package com.vivace.opensw.dto.member;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class LookUpAllMembersDto {
  private String email;
  private String name;
}
