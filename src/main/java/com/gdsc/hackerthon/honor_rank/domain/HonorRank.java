package com.gdsc.hackerthon.honor_rank.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "honor_rank")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class HonorRank {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_user_id", nullable = false)
    private Long firstUserId;

    @Column(name = "second_user_id", nullable = false)
    private Long secondUserId;

    @Column(name = "third_user_id", nullable = false)
    private Long thirdUserId;

    @Column(name = "week", nullable = false)
    private int week;

    @Column(name = "month", nullable = false)
    private int month;
}
