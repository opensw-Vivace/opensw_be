package com.vivace.opensw.entity;

import com.vivace.opensw.global.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Img extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Long id;

    @Column
    @NotNull
    @NotBlank
    private String imgPath;

    @ManyToOne(fetch =FetchType.LAZY,cascade = CascadeType.REMOVE)
    @JoinColumn(name="artifact_id", updatable = false)
    private Artifact artifact;
}
