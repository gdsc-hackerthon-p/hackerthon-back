package com.gdsc.hackerthon.honor_rank.repository;

import com.gdsc.hackerthon.honor_rank.domain.HonorRank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HonorRankRepository extends JpaRepository<HonorRank, Long> {
    HonorRank findByWeekAndMonth(int week, int month);
}
