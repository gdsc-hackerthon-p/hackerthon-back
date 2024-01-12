package com.gdsc.hackerthon.user.repository;

import com.gdsc.hackerthon.user.domain.User;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByGithubEmail(String githubEmail);
    Page<User> findAllByOrderByPointDesc(Pageable pageable);

    boolean existsByGithubId(String githubId);

    @Modifying
    @Transactional
    @Query("update user u set u.point = :point where u.id = :id")
    void updateUserByIdAndPoint(@Param("id") Long id, @Param("point") int point);


    @Modifying
    @Transactional
    @Query("update user u set u.point = :point")
    void updateUsersByPoint(@Param("point") int point);

}
