package com.vivace.opensw.dto.member.request;

import com.vivace.opensw.entity.Member;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record SignUpRequestDto (
    @Email(message = "INVALID_INPUT_FORMAT-유효한 이메일 형식이 아닙니다.")
    @Size
    @NotEmpty(message = "INVALID_INPUT_VALUE-이메일을 입력하세요.")
    String email,
    @Pattern(regexp = "^[가-힣ㄱ-ㅎㅏ-ㅣa-zA-Z0-9]+$", message = "INVALID_INPUT_FORMAT-이름은 한글, 영문 대소문자, 숫자만 사용 가능합니다.")
    @Size
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
