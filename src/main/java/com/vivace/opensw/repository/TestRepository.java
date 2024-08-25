package com.vivace.opensw.repository;

import com.vivace.opensw.entity.Member;
import com.vivace.opensw.entity.Project;
import com.vivace.opensw.entity.Test;
import com.vivace.opensw.model.ArtifactStatus;
import com.vivace.opensw.model.TestStatus;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TestRepository extends JpaRepository<Test, Long> {
    List<Test> findAllByProjectAndStatusOrderByIdDesc(Project project, TestStatus status);
    List<Test> findAllByProjectAndMemberAndStatusOrderByIdDesc(Project project, Member member, TestStatus status);

    public int countByProjectId(Long project_id);
    public int countByProjectIdAndStatus(Long project_id, @NotNull TestStatus status);
}
