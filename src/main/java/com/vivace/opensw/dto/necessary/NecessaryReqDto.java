package com.vivace.opensw.dto.necessary;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class NecessaryReqDto {
    private Long artifactTypeId;
    private boolean necessary;
}
