package com.gdsc.hackerthon.user.repository;

import com.gdsc.hackerthon.user.domain.UserRival;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


public interface RivalRepository extends JpaRepository<UserRival,Long> {

    @Query("SELECT ur FROM user_rival ur WHERE ur.toUserId = :userId OR ur.fromUserId = :userId")
    List<UserRival> findRival(@Param("userId") Long userId);



}


