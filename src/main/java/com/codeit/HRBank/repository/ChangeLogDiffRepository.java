package com.codeit.HRBank.repository;

import com.codeit.HRBank.domain.ChangeLogDiff;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChangeLogDiffRepository extends JpaRepository<ChangeLogDiff, Long> {

    List<ChangeLogDiff> findAllByLog_Id(Long logId);
}
