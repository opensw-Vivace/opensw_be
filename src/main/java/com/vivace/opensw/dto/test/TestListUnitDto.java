package com.vivace.opensw.dto.test;

import com.vivace.opensw.entity.Test;

import java.time.LocalDateTime;

public record TestListUnitDto(
        Long id,
        String title,
        String name,
        LocalDateTime created_at
) {
    public static TestListUnitDto from(Test test) {
        return new TestListUnitDto(
                test.getId(),
                test.getTitle(),
                test.getMember().getName(),
                test.getCreatedAt()
        );
    }
}
