package com.gdsc.hackerthon.honor_rank.application;

import com.gdsc.hackerthon.honor_rank.domain.HonorRank;

public interface HonorRankService {
    //명예의 전당 등록 (주차의 끝마다)
    HonorRank createHonorRank(int month, int week);

    //n월 m주차 1위, 2위, 3위 조회
    HonorRank getHonorRank(int month, int week);


}
