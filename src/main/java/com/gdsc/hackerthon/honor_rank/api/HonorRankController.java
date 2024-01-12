package com.gdsc.hackerthon.honor_rank.api;

import com.gdsc.hackerthon.honor_rank.application.HonorRankService;
import com.gdsc.hackerthon.honor_rank.domain.HonorRank;
import com.gdsc.hackerthon.util.api.ApiResponse;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/honor_rank")
@RequiredArgsConstructor
public class HonorRankController {

    private final HonorRankService honorRankService;

    @GetMapping("/{id}")
    public ApiResponse<List<HonorRank>> getHonorRank(@RequestParam int month, @RequestParam int week) {
        HonorRank honorRank = honorRankService.getHonorRank(month, week);
        /*
        List<Long> userId = Arrays.asList(
                honorRank.getFirstUserId(),
                honorRank.getSecondUserId(),
                honorRank.getThirdUserId()
        );
         */
        List<HonorRank> honorRanks = Arrays.asList(honorRank);
        return ApiResponse.success(honorRanks,"명예의 전당 조회 성공");
    }

    @PostMapping("/{id}")
    public ApiResponse<List<HonorRank>> createHonorRank(@RequestParam int month, @RequestParam int week){
        HonorRank honorRank = honorRankService.createHonorRank(month,week);
        List<HonorRank> honorRanks = Arrays.asList(honorRank);
        return ApiResponse.success(honorRanks,"명예의 전당 등록 성공");
    }

}
