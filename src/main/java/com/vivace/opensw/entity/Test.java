package com.vivace.opensw.entity;

import com.vivace.opensw.global.BaseEntity;
import com.vivace.opensw.model.TestStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Test extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Long id;

    @Column
    @NotNull
    @NotBlank
    private String title;

    @Column
    @NotNull
    private String content;

    @Column
    @NotNull
    @Enumerated(EnumType.STRING)
    private TestStatus status;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "project_id")
    private Project project;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "member_id")
    private Member member;

    public void modifyInfo(String title, String content, TestStatus status) {
        this.title = title;
        this.content = content;
        this.status = status;
    }

    public void clearAssociations() {
        this.project = null;
        this.member = null;
    }
}