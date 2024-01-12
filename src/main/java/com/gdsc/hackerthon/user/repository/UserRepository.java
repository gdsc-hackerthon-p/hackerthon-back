package com.gdsc.hackerthon.user.repository;

import com.gdsc.hackerthon.user.domain.User;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.awt.print.Pageable;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByGithubEmail(String githubEmail);
    Page<User> findAllByOrderByPointDesc(Pageable pageable);

    boolean existsByGithubId(Long githubId);

    @Modifying
    @Transactional
    @Query("update user u set u.point = :point")
    void updateUsersByPoint(int point);
}
