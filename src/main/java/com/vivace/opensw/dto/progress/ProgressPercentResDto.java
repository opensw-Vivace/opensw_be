package com.vivace.opensw.dto.progress;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProgressPercentResDto {
    private float totalPercent;
    private float UPPercent;
}
