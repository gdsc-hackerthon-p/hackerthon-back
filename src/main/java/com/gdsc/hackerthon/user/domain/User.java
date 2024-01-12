package com.gdsc.hackerthon.user.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "user")
@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String nickname;

    @Column(name = "Github_email", nullable = false)
    private String githubEmail;

    @Column(name = "profile_Image_url")
    private String profileImageUrl;

    @Column()
    private int point;

    @Column(name = "commit_streak", nullable = false)
    private int commitStreak;

    @Column(name = "github_url", nullable = false)
    private String githubUrl;
}
