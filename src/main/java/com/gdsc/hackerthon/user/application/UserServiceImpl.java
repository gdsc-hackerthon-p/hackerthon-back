package com.gdsc.hackerthon.user.application;

import com.gdsc.hackerthon.user.domain.User;
import com.gdsc.hackerthon.user.dto.response.ResponseUserDto;
import com.gdsc.hackerthon.user.repository.UserRepository;
import com.gdsc.hackerthon.util.api.ResponseCode;
import com.gdsc.hackerthon.util.exception.UserException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    @Override
    public User createUserWithGithubAccount(User user) {
        if (userRepository.existsById(user.getId())) {
            throw new UserException(ResponseCode.USER_ALREADY_EXIST);
        }
        if(userRepository.existsByGithubId(user.getGithubId())){
            throw new UserException(ResponseCode.USER_ALREADY_EXIST);
        }
        user.updatePoint(calculateUserPoint(user));
        return userRepository.save(user);
    }

    @Override
    public User getUserInfo(Long id) {
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
        for(User user : users){
            user.updatePoint(calculateUserPoint(user));
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

    private void validateUserWithId(Long id){
        if(!userRepository.existsById(id)){
            throw new UserException(ResponseCode.USER_NOT_FOUND);
        }
    }
}
