package com.vivace.opensw.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    // 도메인
    // 타입(상태 코드, "메시지");

    // Default
    ERROR(400, "요청 처리에 실패했습니다."),

    // 400 Bad Request
    // 입력 에러
    INVALID_INPUT_FORMAT(400, "유효하지 않은 형식입니다."),
    INVALID_INPUT_LENGTH(400, "입력 길이가 잘못되었습니다."),
    INVALID_INPUT_VALUE(400, "입력값이 잘못되었습니다."),
    MISSING_PARAMETER(400, "필수 파라미터가 누락되었습니다."),
    // 비밀번호, 비밀번호 확인이 서로 불일치
    PASSWORD_MISMATCH(400, "confirmPw:비밀번호가 일치하지 않습니다."),
    // enum 값이 잘못됨
    INVALID_ENUM_VALUE(400, "enum 값이 잘못되었습니다."),
    // 빈 사진 업로드
    EMPTY_FILE_EXCEPTION(400, "빈 파일입니다."),
    // 업로드 예외
    IO_EXCEPTION_ON_IMAGE_UPLOAD(400, "파일 업로드 시 오류가 발생하였습니다."),
    PUT_OBJECT_EXCEPTION(400, "파일 업로드 시 오류가 발생하였습니다."),
    IO_EXCEPTION_ON_IMAGE_DELETE(400, "파일 삭제 시 오류가 발생하였습니다"),


    // 401 Unauthorized
    // 로그인 상태여야 하는 요청
    NOT_AUTHENTICATED(401, "로그인 상태가 아닙니다."),
    // 권한이 없는 요청을 보냄
    UNAUTHORIZED_REQUEST(401,"권한이 없습니다."),
    // 로그인 시 잘못된 패스워드 입력
    LOGIN_FAIL(401, "로그인에 실패했습니다."),
    // 유효하지 않은 토큰
    INVALID_TOKEN(401, "유효하지 않은 토큰입니다."),
    // 만료된 토큰
    EXPIRED_ACCESS_TOKEN(401,"만료된 액세스 토큰입니다."),
    // 탈퇴한 회원
    MEMBER_STATUS_DELETED(401, "탈퇴한 회원입니다."),
    // 탈퇴한 회원
    NOT_PARTICIPATING(401, "프로젝트에 참여중이지 않습니다."),

    // 404 Not Found
    // 각 리소스를 찾지 못함
    MEMBER_NOT_FOUND(404, "사용자를 찾을 수 없습니다."),
    INVITATION_NOT_FOUND(404, "초대장을 찾을 수 없습니다."),
    ARTIFACT_NOT_FOUND(404, "산출물을 찾을 수 없습니다."),
    ARTIFACT_TYPE_NOT_FOUND(404, "산출물 종류를 찾을 수 없습니다"),
    PROJECT_NOT_FOUND(404,"프로젝트를 찾을 수 없습니다"),
    TODO_NOT_FOUND(404,"할 일을 찾을 수 없습니다"),
    POSITION_NOT_FOUND(404, "역할을 찾을 수 없습니다"),
    TEST_NOT_FOUND(404, "테스트를 찾을 수 없습니다."),

    ISSUE_NOT_FOUND(404,"이슈를 찾을 수 없습니다"),

    NO_FILE_EXTENTION(404, "해당 파일을 찾을 수 없습니다"),
    INVALID_FILE_EXTENTION(404, "유효한 파일이 아닙니다"),


    // 409 Conflict
    // 중복 리소스 생성 시도
    EMAIL_ALREADY_EXISTS(409,   "email:이미 가입된 이메일입니다."),

    // 500 Internal Server Error
    // 외부 API 사용 도중 에러
    EXTERNAL_API_ERROR(500, "외부 API 사용 중 문제가 발생했습니다."),

    ;

    private final int status;
    private final String message;
}