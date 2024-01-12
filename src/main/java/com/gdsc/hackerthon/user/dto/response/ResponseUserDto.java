package com.gdsc.hackerthon.user.dto.response;

import com.gdsc.hackerthon.user.domain.User;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ResponseUserDto {
    private String username;
    private String nickname;
    private String githubId;
    private String profileImageURL;
    private String bio;
    private int commitStreak;
    private int point;

    public static ResponseUserDto from(User user) {
        return ResponseUserDto.builder()
                .nickname(user.getNickname())
                .githubId(user.getGithubId())
                .profileImageURL(user.getProfileImageUrl())
                .bio(user.getBio())
                .commitStreak(user.getCommitStreak())
                .point(user.getPoint())
                .build();
    }
}
