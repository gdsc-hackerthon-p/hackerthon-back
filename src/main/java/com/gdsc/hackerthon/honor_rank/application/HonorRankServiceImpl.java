package com.gdsc.hackerthon.honor_rank.application;

import com.gdsc.hackerthon.honor_rank.domain.HonorRank;
import com.gdsc.hackerthon.honor_rank.repository.HonorRankRepository;
import com.gdsc.hackerthon.user.domain.User;
import com.gdsc.hackerthon.user.repository.UserRepository;
import java.util.ArrayList;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HonorRankServiceImpl implements HonorRankService {
    private final UserRepository userRepository;
    private final HonorRankRepository honorRankRepository;
    @Override
    public HonorRank createHonorRank(int month, int week) {
        Pageable pageable = Pageable.ofSize(3);
        List<User> users = userRepository.findAllByOrderByPointDesc(pageable).getContent();

        return honorRankRepository.save(HonorRank.builder()
                .month(month)
                .week(week)
                .firstUserId(users.get(0).getId())
                .secondUserId(users.get(1).getId())
                .thirdUserId(users.get(2).getId())
                .build());
    }

    @Override
    public HonorRank getHonorRank(int month, int week) {
        return honorRankRepository.findByWeekAndMonth(week, month);

    }
}
