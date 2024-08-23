package com.vivace.opensw.service;

import com.vivace.opensw.dto.member.MyInfoResDto;
import com.vivace.opensw.entity.Member;
import com.vivace.opensw.entity.Position;
import com.vivace.opensw.global.exception.CustomException;
import com.vivace.opensw.global.exception.ErrorCode;
import com.vivace.opensw.model.MemberStatus;
import com.vivace.opensw.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final ParticipateService participateService;

    @Transactional(readOnly = true)
    public MyInfoResDto getCurrentMemberInfo(Long projectId) {
        Member member = getCurrentMember();
        List<String> positionList = participateService.getPositionsByProjectIdAndMember(projectId, member)
                .stream().map(p -> p.getPositionName()).collect(Collectors.toList()); // 포지션명이 담긴 문자열 리스트로 변환
        return MyInfoResDto.from(member, positionList);
    }

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
    public Member getActiveMemberById(Long memberId) throws CustomException {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));
        if (member.getMemberStatus().equals(MemberStatus.DELETED)) {
            throw new CustomException(ErrorCode.MEMBER_STATUS_DELETED);
        }
        return member;
    }

    @Transactional(readOnly = true)
    public Member getActiveMemberByEmail(String email) throws CustomException {
        Member member = getMemberOptionalByEmail(email)
                .orElseThrow(()-> new CustomException(ErrorCode.MEMBER_NOT_FOUND));
        if (member.getMemberStatus().equals(MemberStatus.DELETED)){
            throw new CustomException(ErrorCode.EMAIL_ALREADY_EXISTS);
        }
        return member;
    }

    @Transactional(readOnly = true)
    public Optional<Member> getMemberOptionalByEmail(String email) {
        return memberRepository.findByEmail(email);
    }

    @Transactional
    public void saveMember(Member member) {
        memberRepository.save(member);
    }
}
