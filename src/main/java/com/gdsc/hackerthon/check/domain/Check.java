package com.gdsc.hackerthon.check.domain;

import com.gdsc.hackerthon.user.domain.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity(name = "check")
@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class Check {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "check_date", nullable = false)
    private LocalDateTime checkDate;

    @Column(name = "content", nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id")
    private User user;
}
