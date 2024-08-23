package com.vivace.opensw.dto.project;

import com.vivace.opensw.entity.Member;
import com.vivace.opensw.model.Role;

import java.util.List;

public record ProjectMemberInfoResDto(
        String name,
        Role role,
        List<String> positionList
) {
    public static ProjectMemberInfoResDto from(Member member, Role role, List<String> positionList) {
        return new ProjectMemberInfoResDto(
                member.getName(),
                role,
                positionList
        );
    }
}
