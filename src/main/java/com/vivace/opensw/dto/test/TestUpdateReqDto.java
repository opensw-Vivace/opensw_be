package com.vivace.opensw.dto.test;

import com.vivace.opensw.model.TestStatus;

public record TestUpdateReqDto(
        String title,
        String content,
        TestStatus status
) {
}
