package com.gdsc.hackerthon.user.application;

import com.gdsc.hackerthon.user.domain.User;
import com.gdsc.hackerthon.user.domain.UserRival;
import com.gdsc.hackerthon.user.dto.response.ResponseUserDto;
import com.gdsc.hackerthon.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {
    //비밀번호 암호화
    String encryptPassword(String password);
    //Github에 로그인하고 이를 이용해서 회원가임
    User createUserWithGithubAccount(User user);
    //로그인을 통한 검증
    Boolean login(String githubEmail, String password);
    //회원 정보 조회
    User getUserInfoWithId(Long id);
    //이메일 통한 회원 조회
    User getUserInfoWithEmail(String email);

    User getUserInfoWithUsername(String username);
    //메인화면에서 유저들의 랭킹 조회
    Page<ResponseUserDto> getUsersWithRankingOfPoint(Pageable pageable);
    //회원 탈퇴
    void deleteUser(Long id);
    //회원 닉네임 변경
    void updateUserNickname(Long id, String nickname);
    //모든 회원의 포인트 업데이트
    void updateAllUserPoint();
    //모든 회원의 포인트 초기화 (주기적으로 초기화함)
    void resetAllUserPoint();
    //유저의 포인트 계산
    int calculateUserPoint(User user);
    //List<User> findRival(Long userId);
    //void updatePoint(Long userId, int newPoint);
}
