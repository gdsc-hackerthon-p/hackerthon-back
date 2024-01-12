package com.gdsc.hackerthon.honor_rank.api;

import com.gdsc.hackerthon.honor_rank.application.HonorRankService;
import com.gdsc.hackerthon.honor_rank.domain.HonorRank;
import com.gdsc.hackerthon.util.api.ApiResponse;
import java.util.List;
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

    @GetMapping("/getHonorRank")
    public ApiResponse<HonorRank> getHonorRank(@RequestParam int month, @RequestParam int week) {
        //List<HonorRank> topHonorRank = honorRankService.getHonorRank(month, week);
        return ApiResponse.success(honorRankService.getHonorRank(month, week),"명예의 전당 조회 성공");
    }

    @PostMapping("/postHonorRank")
    public ApiResponse<HonorRank> createHonorRank(@RequestParam int month, @RequestParam int week){
        HonorRank honorRank = honorRankService.createHonorRank(month,week);
        return ApiResponse.success(honorRankService.createHonorRank(month, week),"명예의 전당 등록 성공");
    }

}
