package com.vivace.opensw.controller;

import com.vivace.opensw.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping ("/me")
    public ResponseEntity<?> getMyInfo() {
        return ResponseEntity.ok().body(memberService.getCurrentMemberInfo());
    }
}
