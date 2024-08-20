package com.vivace.opensw.dto.project;

import com.vivace.opensw.entity.Participate;
import com.vivace.opensw.entity.Position;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ProjectGetPositionsDto {
   String name;
   List<Position> Position;
}
