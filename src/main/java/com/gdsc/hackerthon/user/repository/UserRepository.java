package com.gdsc.hackerthon.user.repository;

import com.gdsc.hackerthon.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByGithubEmail(String githubEmail);
}
