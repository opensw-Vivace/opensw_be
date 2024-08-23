package com.vivace.opensw.controller;

import com.vivace.opensw.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping ("/projects/{projectId}/me")
    public ResponseEntity<?> getMyInfo(@PathVariable final Long projectId) {
        return ResponseEntity.ok().body(memberService.getCurrentMemberInfo(projectId));
    }
}
