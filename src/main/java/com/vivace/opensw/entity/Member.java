package com.vivace.opensw.entity;

import com.vivace.opensw.global.BaseEntity;
import com.vivace.opensw.model.MemberStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Member extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Long id;

    @Column(unique = true)
    @Email
    @NotNull
    @NotBlank
    private String email;

    @Column
    @NotNull
    @NotBlank
    private String name;

    @Column
    @NotNull
    @NotBlank
    private String pw;

    @Column
    @NotNull
    @Enumerated(EnumType.STRING)
    private MemberStatus memberStatus;
}
