package com.vivace.opensw.service;

import com.vivace.opensw.dto.invitation.InvitationReqDto;
import com.vivace.opensw.dto.position.PositionListReqDto;
import com.vivace.opensw.entity.Invitation;
import com.vivace.opensw.entity.Member;
import com.vivace.opensw.entity.Participate;
import com.vivace.opensw.entity.Position;
import com.vivace.opensw.global.exception.CustomException;
import com.vivace.opensw.global.exception.ErrorCode;
import com.vivace.opensw.model.Role;
import com.vivace.opensw.repository.InvitationRepository;
import com.vivace.opensw.repository.MemberRepository;
import com.vivace.opensw.repository.ParticipateRepository;
import com.vivace.opensw.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.digester.ArrayStack;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InvitationService {

    private final InvitationRepository InvitationRepository;

    private final ProjectRepository projectRepository;

    private final MemberRepository memberRepository;
    private final InvitationRepository invitationRepository;
    private final ParticipateRepository participateRepository;
    /**
     *  Invitation(초대장)에 관련된 crud 담당
     */

    /**
     * 초대장 발송.db에 저장. add
     */
    public void send(InvitationReqDto InvitationReqDto){
        Invitation Invitation=new Invitation().builder()
                .project(projectRepository.findById(InvitationReqDto.getProjectId()).get())
                .sender(memberRepository.findById(InvitationReqDto.getSenderId()).get())
                .receiver(memberRepository.findById(InvitationReqDto.getReceiverId()).get())
                .createdAt(LocalDateTime.now())
                        .build();


        InvitationRepository.save(Invitation);
    }

    /**
     * 특정 멤버가 받은 초대장. 수신자의id로 조회.
     */
    public List<InvitationReqDto> findByReceiverId(Long id){
        List<Invitation> InvitationList= InvitationRepository.findByReceiverId(id).get();
        List<InvitationReqDto> invitationReqDtoList =new ArrayList<>();
        InvitationReqDto InvitationReqDto;
        for(Invitation Invitation:InvitationList){ //하나씩 꺼내서 dto로 변환 후 리스트에 삽입
            InvitationReqDto =new InvitationReqDto().builder()
                    .id(Invitation.getId())
                    .projectId(Invitation.getProject().getId())
                    .senderId(Invitation.getSender().getId())
                    .receiverId(Invitation.getReceiver().getId())
                    .createdAt(Invitation.getCreatedAt())
                    .updateAt(Invitation.getUpdatedAt())
                    .build();

            invitationReqDtoList.add(InvitationReqDto);
        }

        //리스트 반환
        return invitationReqDtoList;
    }


    /**
     * 초대장 수락
     */
    public void accept(Long id, PositionListReqDto positionListReqDto){
        //초대장 엔티티 가져옴
        Invitation invitation=invitationRepository.findById(id)
                .orElseThrow(()->new CustomException(ErrorCode.INVITATION_NOT_FOUND));


        // Participate 테이블에 튜플 추가
        Participate participate=new Participate().builder()
                .role(Role.ROLE_MEMBER) //기본적으로 권한은 멤버로 추가
                .project(invitation.getProject())
                .build();

        // Position 테이블에 튜플 추가
        List<Position> positionList=new LinkedList<>();
        for(String positionName:positionListReqDto.getPositionList()){
            Position position=new Position().builder()
                    .position(positionName)
                    .member(invitation.getReceiver())
                    .participate(participate)
                    .build();
            positionList.add(position);
        }

        participate.updatePosition(positionList);

        //참여 목록에 추가.
        //cascade.ALL로 position역시 추가되는지 확인해보기.
        participateRepository.save(participate);

        //수락 후 초대장 삭제
        invitationRepository.deleteById(id);

    }


    /**
     * 초대장 삭제(거절)
     */
    public void deleteById(Long id){
        invitationRepository.deleteById(id);
    }



}
