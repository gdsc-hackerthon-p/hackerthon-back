package com.gdsc.hackerthon.check.repository;

import com.gdsc.hackerthon.check.domain.Check;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface CheckRepository extends JpaRepository<Check, Long> {
    @Query("SELECT c FROM check c WHERE c.checkDate >= :startOfDay AND c.checkDate < :endOfDay ORDER BY c.checkDate DESC")
    Page<Check> findByCheckDateBetweenOrderByCheckDateDesc(Pageable pageable, @Param("startOfDay") LocalDateTime startOfDay, @Param("endOfDay") LocalDateTime endOfDay);
}
