package com.vivace.opensw.dto.project;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ProjectGetMembersDto {
   String name;
   List<String> Position;
}
