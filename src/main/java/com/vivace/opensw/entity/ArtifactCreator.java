package com.vivace.opensw.entity;

import com.vivace.opensw.global.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArtifactCreator extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Long id;

    @Column
    @ColumnDefault("false")
    @Builder.Default
    private Boolean isComplete = false;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "member_id", updatable = false)
    private Member member;

    @Setter //artifact service.save 참고
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "artifact_id", updatable = false)
    private Artifact artifact;

}
