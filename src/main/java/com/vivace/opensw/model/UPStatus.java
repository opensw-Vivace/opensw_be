package com.vivace.opensw.model;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UPStatus {

    private UPPhaseStatus phase; //현재 phase

    private int phaseNum; //현재 몇 번째 반복인지.
}
