package com.vivace.opensw.service;


import com.vivace.opensw.dto.member.LoginReqDto;
import com.vivace.opensw.dto.member.SignUpReqDto;
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

import java.util.Collections;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthService {
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final MemberService memberService;

    public void signUp(SignUpReqDto reqDto) {
        if (!reqDto.pw().equals(reqDto.confirmPw())) {
            throw new CustomException(ErrorCode.PASSWORD_MISMATCH);
        }

       Optional<Member> memberOptional = memberService.getMemberOptionalByEmail(reqDto.email());
       if (memberOptional.isPresent()) {
            if (memberOptional.get().getMemberStatus().equals(MemberStatus.DELETED)){
                throw new CustomException(ErrorCode.MEMBER_STATUS_DELETED);
            }
            throw new CustomException(ErrorCode.EMAIL_ALREADY_EXISTS);
        }

        //Member 객체 생성 후 저장
        Member member = reqDto.toEntity(encodePassword(reqDto.pw()));
        memberService.saveMember(member);
    }

    public String login(LoginReqDto reqDto){
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(reqDto.email(), reqDto.pw(), Collections.emptyList())
            );
            return jwtUtil.generateAccessToken(authentication);
        } catch (BadCredentialsException e){
            throw new CustomException(ErrorCode.LOGIN_FAIL);
        }
    }

    public String encodePassword(String password){
        return passwordEncoder.encode(password);
    }
}
