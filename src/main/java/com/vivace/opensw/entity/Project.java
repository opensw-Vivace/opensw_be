package com.vivace.opensw.entity;

import com.vivace.opensw.model.ProjectStatus;
import com.vivace.opensw.model.UPPhaseStatus;
import com.vivace.opensw.model.UPStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Project {
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
    private String teamName;

    @Column
    @NotNull
    private String description;

    @Column
    @NotNull
    private LocalDate deadline;

    @Column
    @NotNull
    private int iterationLen;

    @Column
    @NotNull
    @Enumerated(EnumType.STRING)
    private ProjectStatus status;

    /*
    Unified Process를 위한 필드 설정
     */
    @Column
    @NotNull
    @Enumerated(EnumType.STRING)
    private UPPhaseStatus phase; //현재 phase

    @Column
    @NotNull
    private int phaseNum; //현재 phase의 몇 번째 반복인지.

    @Column
    @NotNull
    private LocalDate iterStartDate; //반복 시작일.

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Participate> participateList;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ToDo> toDoList;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Issue> issueList;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Invitation> invitationList;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Artifact> artifactList;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<NecessaryArtifactType> necessaryArtifactTypeList;

    @Builder
    public Project(Long id, String title, String teamName, String description, LocalDate deadline, int iterationLen) {
        this.id = id;
        this.title = title;
        this.teamName = teamName;
        this.description = description;
        this.deadline = deadline;
        this.iterationLen = iterationLen;
        this.status = ProjectStatus.IN_PROGRESS;
        this.participateList = new ArrayList<>();
        this.toDoList = new ArrayList<>();
        this.issueList = new ArrayList<>();
        this.invitationList = new ArrayList<>();
        this.artifactList = new ArrayList<>();
        this.necessaryArtifactTypeList = new ArrayList<>();
    }

    public void updateUPStatus(UPStatus upStatus){
        phase=upStatus.getPhase();
        phaseNum =upStatus.getPhaseNum();
    }

    public void clearIterStartDate(){
        iterStartDate=LocalDate.now();
    }
}
