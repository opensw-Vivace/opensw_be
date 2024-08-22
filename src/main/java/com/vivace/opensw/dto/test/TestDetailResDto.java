package com.vivace.opensw.dto.test;

import com.vivace.opensw.entity.Test;
import com.vivace.opensw.model.TestStatus;

import java.time.LocalDateTime;

public record TestDetailResDto(
        String title,
        String content,
        TestStatus status,
        String name,
        LocalDateTime created_at,
        LocalDateTime updated_at
) {
    public static TestDetailResDto from(Test test) {
        return new TestDetailResDto(
                test.getTitle(),
                test.getContent(),
                test.getStatus(),
                test.getMember().getName(),
                test.getCreatedAt(),
                test.getUpdatedAt()
        );
    }
}
