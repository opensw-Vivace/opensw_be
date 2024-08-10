package com.vivace.opensw.controller;

import com.vivace.opensw.dto.InviteDto;
import com.vivace.opensw.service.InviteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class InviteController {

    private final InviteService inviteService;


    /**
     * 초대장 발송
     */
    @PostMapping("/invitations")
    public void send(@RequestBody InviteDto inviteDto){
        inviteService.send(inviteDto);
        System.out.println(inviteDto.toString());
        System.out.println("hello");
    }

    /**
     * 테스트용. 토큰 적용 전까지 id직접 입력해서 확인
     */
    @GetMapping("/invitations/{receiverId}")
    public ResponseEntity<List<InviteDto>> findByReceiverId(@PathVariable Long receiverId){
        return ResponseEntity.status(HttpStatus.OK).body(inviteService.findByReceiverId(receiverId)) ;
    }

    /**
     * 초대장 거절. deletemapping 사용 가능한지 테스트 해볼 것
     */
    @DeleteMapping("/invitations/{invitationId}")
    public void reject(@PathVariable Long invitationId){
        inviteService.deleteById(invitationId);
    }



}
