package com.vivace.opensw.dto.member;

import com.vivace.opensw.entity.Member;

import java.util.List;

public record MyInfoResDto (
        String name,
        String email,
        List<String> positionList
) {
    public static MyInfoResDto from(Member member, List<String> positionList) {
        return new MyInfoResDto(
                member.getName(),
                member.getEmail(),
                positionList
        );
    }
}
