package com.vivace.opensw.dto.member;

import com.vivace.opensw.entity.Member;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public record SignUpReqDto(
    @Email(message = "INVALID_INPUT_FORMAT-유효한 이메일 형식이 아닙니다.")
    @NotEmpty(message = "INVALID_INPUT_VALUE-이메일을 입력하세요.")
    String email,
    @NotEmpty(message = "INVALID_INPUT_VALUE-이름을 입력하세요.")
    String name,
    @NotEmpty(message = "INVALID_INPUT_VALUE-비밀번호를 입력하세요.")
    String pw,
    @NotEmpty(message = "INVALID_INPUT_VALUE-비밀번호 확인을 입력하세요.")
    String confirmPw
) {
    public Member toEntity(String encodedPassword){
        return Member.builder()
                .email(this.email)
                .name(this.name)
                .pw(encodedPassword)
                .build();
    }
}
