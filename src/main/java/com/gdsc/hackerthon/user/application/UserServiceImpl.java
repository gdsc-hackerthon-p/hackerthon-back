package com.gdsc.hackerthon.user.application;

import com.gdsc.hackerthon.github.application.GithubService;
import com.gdsc.hackerthon.user.domain.User;
import com.gdsc.hackerthon.user.dto.response.ResponseUserDto;
import com.gdsc.hackerthon.user.repository.UserRepository;
import com.gdsc.hackerthon.util.api.ResponseCode;
import com.gdsc.hackerthon.util.exception.UserException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kohsuke.github.GitHub;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final GithubService githubService;

    @Override
    public String encryptPassword(String password) {
        return passwordEncoder.encode(password);
    }

    @Override
    public User createUserWithGithubAccount(User user) {
        if (userRepository.existsByGithubId(user.getGithubId())) {
            throw new UserException(ResponseCode.USER_ALREADY_EXIST);
        }
        user.updatePoint(calculateUserPoint(user));
        return userRepository.save(user);
    }

    @Override
    public Boolean login(String username, String password) {
        User user = userRepository.findByUsername(username).orElseThrow(()
                -> new UserException(ResponseCode.USER_LOGIN_FAILURE));

        return passwordEncoder.matches(password, user.getPassword());
    }


    @Override
    public User getUserInfoWithEmail(String email) {
        return userRepository.findByGithubEmail(email).orElseThrow(()
                -> new UserException(ResponseCode.USER_NOT_FOUND));
    }

    @Override
    public User getUserInfoWithUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(()
                -> new UserException(ResponseCode.USER_NOT_FOUND));
    }

    @Override
    public User getUserInfoWithId(Long id) {
        return userRepository.findById(id).orElseThrow(()
                -> new UserException(ResponseCode.USER_NOT_FOUND));
    }

    @Override
    public Page<ResponseUserDto> getUsersWithRankingOfPoint(Pageable pageable) {
        return userRepository.findAllByOrderByPointDesc(pageable)
                .map(ResponseUserDto::from);
    }

    @Override
    public void deleteUser(Long id) {
        validateUserWithId(id);
        userRepository.deleteById(id);
    }

    @Override
    public void updateUserNickname(Long id, String nickname) {
        validateUserWithId(id);
        User user = userRepository.getReferenceById(id);
        user.updateNickname(nickname);
        userRepository.save(user);

    }

    @Override
    public void updateAllUserPoint() {
        List<User> users = userRepository.findAll();
        for (User user : users) {
            try {
                int commitCount = githubService.getCommitStreak(user.getGithubId());
                userRepository.updateUserByIdAndPoint(user.getId(), commitCount * 100);
            } catch (IOException e) {
                log.error("Github API Error");
            }
        }
    }

    @Override
    public void resetAllUserPoint() {
        userRepository.updateUsersByPoint(0);
    }

    @Override
    public int calculateUserPoint(User user) {
        return user.getCommitStreak() * 100; //임시
    }

    private void validateUserWithId(Long id) {
        if (!userRepository.existsById(id)) {
            throw new UserException(ResponseCode.USER_NOT_FOUND);
        }
    }

    public List<User> findRival(Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            return null;
        }
        int userPoint = user.getPoint();
        return userRepository.findByPoint(userPoint);
    }

    public void updatePoint(Long userId, int newPoint) {
        User userPoint = userRepository.findById(userId).orElse(null);
        if (userPoint != null) {
            userPoint.getPoint();
            userRepository.save(userPoint);
        }
    }
}


