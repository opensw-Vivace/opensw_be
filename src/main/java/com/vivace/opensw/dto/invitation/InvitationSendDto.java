package com.vivace.opensw.dto.invitation;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InvitationSendDto {
    private Long projectId;
    private Long receiverId;
}
