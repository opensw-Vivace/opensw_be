package com.vivace.opensw.dto.test;

import com.vivace.opensw.entity.Test;

import java.time.LocalDateTime;

public record TestListUnitDto(
        String title,
        String name,
        LocalDateTime created_at
) {
    public static TestListUnitDto from(Test test) {
        return new TestListUnitDto(
                test.getTitle(),
                test.getMember().getName(),
                test.getCreatedAt()
        );
    }
}
