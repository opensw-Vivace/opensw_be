package com.vivace.opensw.service;

import com.vivace.opensw.entity.Member;
import com.vivace.opensw.global.exception.CustomException;
import com.vivace.opensw.global.exception.ErrorCode;
import com.vivace.opensw.model.MemberStatus;
import com.vivace.opensw.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    @Transactional(readOnly = true)
    public Member getCurrentMember() throws CustomException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Member member = memberRepository.findByEmail(authentication.getName())
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_AUTHENTICATED));
        if (member.getMemberStatus().equals(MemberStatus.DELETED)) {
            throw new CustomException(ErrorCode.MEMBER_STATUS_DELETED);
        }
        return member;
    }

    @Transactional(readOnly = true)
    public Member getActiveMemberById(Long memberId){
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));
        if (member.getMemberStatus().equals(MemberStatus.DELETED)) {
            throw new CustomException(ErrorCode.MEMBER_STATUS_DELETED);
        }
        return member;
    }

    @Transactional(readOnly = true)
    public Member getActiveMemberByEmail(String email) {
        Member member = getMemberOptionalByEmail(email)
                .orElseThrow(()-> new CustomException(ErrorCode.MEMBER_NOT_FOUND));
        if (member.getMemberStatus().equals(MemberStatus.DELETED)){
            throw new CustomException(ErrorCode.EMAIL_ALREADY_EXISTS);
        }
        return member;
    }

    @Transactional(readOnly = true)
    public Optional<Member> getMemberOptionalByEmail(String email){
        return memberRepository.findByEmail(email);
    }

    public void saveMember(Member member) {
        memberRepository.save(member);
    }
}
