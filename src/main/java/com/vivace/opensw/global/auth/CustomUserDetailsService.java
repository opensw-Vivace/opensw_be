package com.vivace.opensw.global.auth;

import com.vivace.opensw.entity.Member;
import com.vivace.opensw.global.exception.CustomException;
import com.vivace.opensw.global.exception.ErrorCode;
import com.vivace.opensw.model.MemberStatus;
import com.vivace.opensw.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws CustomException {
        return new CustomUserDetails(loadMemberByEmail(email));
    }

    public Member loadMemberByEmail(String email) throws CustomException {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(()-> new CustomException(ErrorCode.MEMBER_NOT_FOUND));
        if (member.getMemberStatus().equals(MemberStatus.DELETED)){
            throw new CustomException(ErrorCode.MEMBER_STATUS_DELETED);
        }
        return member;
    }
}
