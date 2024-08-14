package com.vivace.opensw.service;


import com.vivace.opensw.dto.member.request.LoginRequestDto;
import com.vivace.opensw.dto.member.request.SignUpRequestDto;
import com.vivace.opensw.entity.Member;
import com.vivace.opensw.global.auth.JwtUtil;
import com.vivace.opensw.global.exception.CustomException;
import com.vivace.opensw.global.exception.ErrorCode;
import com.vivace.opensw.model.MemberStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthService {
    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public void signUp(SignUpRequestDto requestDto) {
        if (!requestDto.pw().equals(requestDto.confirmPw())) {
            throw new CustomException(ErrorCode.PASSWORD_MISMATCH);
        }

       Optional<Member> memberOptional = memberService.getMemberOptionalByEmail(requestDto.email());
       if (memberOptional.isPresent()) {
            if (memberOptional.get().getMemberStatus().equals(MemberStatus.DELETED)){
                throw new CustomException(ErrorCode.MEMBER_STATUS_DELETED);
            }
            throw new CustomException(ErrorCode.EMAIL_ALREADY_EXISTS);
        }

        //Member 객체 생성 후 저장
        Member member = requestDto.toEntity(encodePassword(requestDto.pw()));
        memberService.saveMember(member);
    }

    public String login(LoginRequestDto requestDto){
        try{
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            requestDto.email(), requestDto.password()
                    )
            );
            Member member = memberService.getActiveMemberByEmail(authentication.getName());
            String accessToken = jwtUtil.generateAccessToken(authentication);
            memberService.updateAccessToken(member, accessToken);
            return accessToken;
        } catch (BadCredentialsException e){
            throw new CustomException(ErrorCode.LOGIN_FAIL);
        }
    }

    public String encodePassword(String password){
        return passwordEncoder.encode(password);
    }

}
