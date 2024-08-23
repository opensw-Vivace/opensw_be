package com.vivace.opensw.repository;

import com.vivace.opensw.entity.Member;
import com.vivace.opensw.entity.Project;
import com.vivace.opensw.entity.Test;
import com.vivace.opensw.model.TestStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TestRepository extends JpaRepository<Test, Long> {
    List<Test> findAllByProjectAndStatusOrderByIdDesc(Project project, TestStatus status);
    List<Test> findAllByProjectAndMemberAndStatusOrderByIdDesc(Project project, TestStatus status, Member member);
}
