package com.gdsc.hackerthon.user.api;

import com.gdsc.hackerthon.user.application.UserService;
import com.gdsc.hackerthon.user.domain.User;
import com.gdsc.hackerthon.util.api.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@Tag(name = "user", description = "유저 API")
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    //유저 정보 조회
    @Operation(summary = "[유저] 유저 정보 조회", description = "유저의 정보를 조회합니다.")
    @GetMapping("/{userId}/info")
    public ApiResponse<Long> getUserInfo(@PathVariable Long userId){
        return ApiResponse.success(userService.getUserInfo(userId).getId(), "유저 정보 조회 성공");
    }

    //Github로 로그인 후 유저 생성
    @Operation(summary = "[유저] Github로 로그인 후 유저 생성", description = "Github로 로그인 후 유저를 생성합니다.")
    @PostMapping("/github")
    public ApiResponse<Long> createUserWithGithubAccount(@RequestBody String githubId){
        User temp = User.builder().build();//임시 (이 부분의 Oauth가 구현이 되어야 함)
        return ApiResponse.success(userService.createUserWithGithubAccount(temp).getId(), "유저 생성 성공");
    }

    //유저 닉네임 변경
    @Operation(summary = "[유저] 유저 닉네임 변경", description = "유저의 닉네임을 변경합니다.")
    @PutMapping("/{userId}/nickname")
    public ApiResponse<Void> updateUserNickname(@PathVariable Long userId, String nickname){
        userService.updateUserNickname(userId, nickname);
        return ApiResponse.success(null, "유저 닉네임 변경 성공");
    }

    //유저 탈퇴
    @Operation(summary = "[유저] 유저 탈퇴", description = "유저를 탈퇴시킵니다.")
    @DeleteMapping("/{userId}")
    public ApiResponse<Void> deleteUser(@PathVariable Long userId){
        userService.deleteUser(userId);
        return ApiResponse.success(null, "유저 탈퇴 성공");
    }

    //유저 랭킹 조회
    @Operation(summary = "[유저] 유저 랭킹 조회", description = "유저의 랭킹을 조회합니다.")
    @GetMapping("/ranking")
    public ApiResponse<Page<User>> getUsersWithRankingOfPoint(@RequestParam int size, @RequestParam int page){
        return ApiResponse.success(userService.getUsersWithRankingOfPoint(Pageable.ofSize(size).withPage(page)), "유저 랭킹 조회 성공");
    }


}
