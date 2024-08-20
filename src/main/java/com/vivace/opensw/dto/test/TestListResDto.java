package com.vivace.opensw.dto.test;

import java.util.List;

public record TestListResDto (
    List<TestListUnitDto> notStartedTestList,
    List<TestListUnitDto> completedTestList
){
}
