package com.vivace.opensw.controller;

import com.vivace.opensw.dto.InvitationDto;
import com.vivace.opensw.service.InvitationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class InvitationController {

    private final InvitationService InvitationService;


    /**
     * 초대장 발송
     * 발송 되었는데 500에러. responseEntity설정 해야할 듯
     */
    @PostMapping("/invitations")
    public ResponseEntity send(@RequestBody InvitationDto InvitationDto){
        InvitationService.send(InvitationDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * 테스트용. 토큰 적용 전까지 id직접 입력해서 확인
     */
    @GetMapping("/invitations/{receiverId}")
    public ResponseEntity<List<InvitationDto>> findByReceiverId(@PathVariable Long receiverId){
        return ResponseEntity.status(HttpStatus.OK).body(InvitationService.findByReceiverId(receiverId)) ;
    }

    /**
     * 초대장 거절. deletemapping 사용 가능한지 테스트 해볼 것
     * 404 not found 떴는데 삭제됨. 왜??
     */
    @DeleteMapping("/invitations/{invitationId}")
    public ResponseEntity reject(@PathVariable Long invitationId){

        InvitationService.deleteById(invitationId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }



}
