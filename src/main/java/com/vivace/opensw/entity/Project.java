package com.vivace.opensw.entity;

import com.vivace.opensw.model.ProjectStatus;
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
    private ProjectStatus projectStatus;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Participate> participateList;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ToDo> toDoList;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Issue> issueList;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Invite> inviteList;

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
        this.projectStatus = ProjectStatus.IN_PROGRESS;
        this.participateList = new ArrayList<>();
        this.toDoList = new ArrayList<>();
        this.issueList = new ArrayList<>();
        this.inviteList = new ArrayList<>();
        this.artifactList = new ArrayList<>();
        this.necessaryArtifactTypeList = new ArrayList<>();
    }
}
