package com.gdsc.hackerthon.user.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity(name = "user")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(unique = true, nullable = false)
    private String nickname;

    @Column(name = "Github_email", nullable = false)
    private String githubEmail;

    @Column(name = "profile_Image_url")
    private String profileImageUrl;

    @Column()
    private int point;

    @Column(name = "bio")
    private String bio;

    @Column(name = "commit_streak", nullable = false)
    private int commitStreak;

    @Column(name = "github_url", nullable = false)
    private String githubUrl;

    @Column(name = "github_id", nullable = false)
    private String githubId;

    public void updateNickname(String nickname) {
        this.nickname = nickname;
    }

    public void updatePoint(int point) {
        this.point = point;
    }

    @ElementCollection(fetch = FetchType.EAGER) //roles 컬렉션
    private List<String> roles = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(java.util.stream.Collectors.toList());
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
