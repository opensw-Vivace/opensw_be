package com.vivace.opensw.dto.member;

import com.vivace.opensw.entity.Member;

public record MemberInfoResDto (
        Long id,
        String name,
        String email
){
    public static MemberInfoResDto from(Member member) {
        return new MemberInfoResDto(
                member.getId(),
                member.getName(),
                member.getEmail()
        );
    }
}
