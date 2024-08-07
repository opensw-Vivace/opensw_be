package com.vivace.opensw.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class MemberEntity extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String email;

    @Column
    private String name;

    @Column
    private String pw;

    @Column
    private Boolean status;

    @OneToMany(mappedBy = "memberEntity")
    private List<PositionEntity> positionEntityList;

    @OneToMany(mappedBy = "memberEntity")
    private List<IssueEntity> issueEntityList;

    @OneToMany(mappedBy = "memberEntity")
    private List<ToDoEntity> toDoEntityList;

    @OneToMany(mappedBy = "senderEntity")
    private List<InviteEntity> receivedInvitationList;

    @OneToMany(mappedBy = "receiverEntity")
    private List<InviteEntity> sendInvicationList;

}
