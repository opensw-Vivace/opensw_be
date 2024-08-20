package com.vivace.opensw.dto.invitation;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class InvitationSendDto {
    private Long projectId;
    private Long receiverId;
}
