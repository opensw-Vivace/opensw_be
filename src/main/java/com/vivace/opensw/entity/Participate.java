package com.vivace.opensw.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vivace.opensw.global.BaseEntity;
import com.vivace.opensw.model.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Participate extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Long id;

    @Column
    @NotNull
    @Enumerated(EnumType.STRING)
    private Role role;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", updatable = false)
    @JsonIgnore
    private Project project;

    @OneToMany(mappedBy = "participate", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Position> positionList;
    public void updatePosition(List<Position> positionList){
        this.positionList=positionList;
    }
}
