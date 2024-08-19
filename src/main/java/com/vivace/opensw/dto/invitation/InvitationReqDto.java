package com.vivace.opensw.dto.invitation;


import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InvitationReqDto {
    private Long id;

    private Long projectId;

    private Long senderId;

    private Long receiverId;

    private LocalDateTime createdAt;

    private LocalDateTime updateAt;

}
