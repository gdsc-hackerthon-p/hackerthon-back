package com.gdsc.hackerthon.user.application;

import com.gdsc.hackerthon.user.domain.User;
import com.gdsc.hackerthon.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {
    User createUserWithGithubAccount(User user);
    User getUser(Long id);
    List<User> getUsersWithRankingOfPoint();
    void deleteUser(Long id);
    void updateUserNickname(Long id, String nickname);
    User resetAllUserPoint();
    int calculateUserPoint(User user);
}
