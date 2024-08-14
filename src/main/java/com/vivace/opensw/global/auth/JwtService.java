package com.vivace.opensw.global.auth;

import com.vivace.opensw.entity.Member;
import com.vivace.opensw.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
@RequiredArgsConstructor
public class JwtService {
    private final MemberRepository memberRepository;

    public void updateAccessToken(Member member, String accessToken) {
        member.updateAccessToken(accessToken);
        memberRepository.save(member);
    }
}
