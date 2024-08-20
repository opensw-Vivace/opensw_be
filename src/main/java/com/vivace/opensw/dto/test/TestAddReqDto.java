package com.vivace.opensw.dto.test;

import com.vivace.opensw.entity.Member;
import com.vivace.opensw.entity.Project;
import com.vivace.opensw.entity.Test;
import com.vivace.opensw.model.TestStatus;

public record TestAddReqDto(
        String title,
        String content,
        TestStatus status
) {
    public static Test toEntity(TestAddReqDto testAddReqDto, Project project, Member member) {
        return Test.builder()
                .title(testAddReqDto.title)
                .content(testAddReqDto.content)
                .status(testAddReqDto.status)
                .project(project)
                .member(member)
                .build();
    }
}
