package com.vivace.opensw.dto.invitation;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InvitationSendDto {
    private Long projectId;
    private Long receiverId;
}
