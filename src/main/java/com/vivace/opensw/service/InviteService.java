package com.vivace.opensw.service;

import com.vivace.opensw.dto.InviteDto;
import com.vivace.opensw.entity.Invite;
import com.vivace.opensw.repository.InviteRepository;
import com.vivace.opensw.repository.MemberRepository;
import com.vivace.opensw.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InviteService {

    private final InviteRepository inviteRepository;

    private final ProjectRepository projectRepository;

    private final MemberRepository memberRepository;

    /**
     *  invite(초대장)에 관련된 crud 담당
     */

    /**
     * 초대장 발송.db에 저장. add
     */
    public void send(InviteDto inviteDto){
        Invite invite=new Invite().builder()
                .project(projectRepository.findById(inviteDto.getProjectId()).get())
                .sender(memberRepository.findById(inviteDto.getSenderId()).get())
                .receiver(memberRepository.findById(inviteDto.getReceiverId()).get())
                .createdAt(LocalDateTime.now())
                        .build();


        inviteRepository.save(invite);
    }

    /**
     * 특정 멤버가 받은 초대장. 수신자의id로 조회.
     */
    public List<InviteDto> findByReceiverId(Long id){
        List<Invite> inviteList= inviteRepository.findByReceiverId(id).get();
        List<InviteDto> inviteDtoList=new ArrayList<>();
        InviteDto inviteDto;
        for(Invite invite:inviteList){ //하나씩 꺼내서 dto로 변환 후 리스트에 삽입
            inviteDto=new InviteDto().builder()
                    .id(invite.getId())
                    .projectId(invite.getProject().getId())
                    .senderId(invite.getSender().getId())
                    .receiverId(invite.getReceiver().getId())
                    .createdAt(invite.getCreatedAt())
                    .updateAt(invite.getUpdatedAt())
                    .build();

            inviteDtoList.add(inviteDto);
        }

        //리스트 반환
        return inviteDtoList;
    }


    /**
     * 초대장 수락
     */


    /**
     * 초대장 삭제(거절)
     */
    public void deleteById(Long id){
        inviteRepository.deleteById(id);
    }



}
