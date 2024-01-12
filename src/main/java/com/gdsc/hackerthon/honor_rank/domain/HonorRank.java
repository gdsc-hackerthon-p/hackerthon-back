package com.gdsc.hackerthon.honor_rank.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "honor_rank")
@Getter
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

    @Column(name = "year", nullable = false)
    private int year;
}
