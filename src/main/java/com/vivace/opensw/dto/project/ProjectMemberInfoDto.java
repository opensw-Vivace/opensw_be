package com.vivace.opensw.dto.project;

import com.vivace.opensw.entity.Member;
import com.vivace.opensw.model.Role;

import java.util.List;

public record ProjectMemberInfoDto(
        String name,
        Role role,
        List<String> positionList
) {
    public static ProjectMemberInfoDto from(Member member, Role role, List<String> positionList) {
        return new ProjectMemberInfoDto(
                member.getName(),
                role,
                positionList
        );
    }
}
