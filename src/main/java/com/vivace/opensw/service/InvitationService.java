package com.vivace.opensw.service;

import com.vivace.opensw.dto.InvitationDto;
import com.vivace.opensw.entity.Invitation;
import com.vivace.opensw.repository.InvitationRepository;
import com.vivace.opensw.repository.MemberRepository;
import com.vivace.opensw.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InvitationService {

    private final InvitationRepository InvitationRepository;

    private final ProjectRepository projectRepository;

    private final MemberRepository memberRepository;

    /**
     *  Invitation(초대장)에 관련된 crud 담당
     */

    /**
     * 초대장 발송.db에 저장. add
     */
    public void send(InvitationDto InvitationDto){
        Invitation Invitation=new Invitation().builder()
                .project(projectRepository.findById(InvitationDto.getProjectId()).get())
                .sender(memberRepository.findById(InvitationDto.getSenderId()).get())
                .receiver(memberRepository.findById(InvitationDto.getReceiverId()).get())
                .createdAt(LocalDateTime.now())
                        .build();


        InvitationRepository.save(Invitation);
    }

    /**
     * 특정 멤버가 받은 초대장. 수신자의id로 조회.
     */
    public List<InvitationDto> findByReceiverId(Long id){
        List<Invitation> InvitationList= InvitationRepository.findByReceiverId(id).get();
        List<InvitationDto> InvitationDtoList=new ArrayList<>();
        InvitationDto InvitationDto;
        for(Invitation Invitation:InvitationList){ //하나씩 꺼내서 dto로 변환 후 리스트에 삽입
            InvitationDto=new InvitationDto().builder()
                    .id(Invitation.getId())
                    .projectId(Invitation.getProject().getId())
                    .senderId(Invitation.getSender().getId())
                    .receiverId(Invitation.getReceiver().getId())
                    .createdAt(Invitation.getCreatedAt())
                    .updateAt(Invitation.getUpdatedAt())
                    .build();

            InvitationDtoList.add(InvitationDto);
        }

        //리스트 반환
        return InvitationDtoList;
    }


    /**
     * 초대장 수락
     */


    /**
     * 초대장 삭제(거절)
     */
    public void deleteById(Long id){
        InvitationRepository.deleteById(id);
    }



}
