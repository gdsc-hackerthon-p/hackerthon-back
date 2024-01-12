package com.gdsc.hackerthon.user.api;

import com.gdsc.hackerthon.github.application.GithubService;
import com.gdsc.hackerthon.user.application.UserService;
import com.gdsc.hackerthon.user.domain.User;
import com.gdsc.hackerthon.user.dto.request.CreateUserDto;
import com.gdsc.hackerthon.user.dto.response.ResponseUserDto;
import com.gdsc.hackerthon.util.api.ApiResponse;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.models.annotations.OpenAPI31;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.kohsuke.github.GHUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Tag(name = "User", description = "유저 API")
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final GithubService githubService;
    //유저 정보 조회
    @GetMapping("/info/{userId}")
    @Operation(summary = "[유저] 유저 정보 조회", description = "유저의 정보를 조회합니다.")
    public ApiResponse<ResponseUserDto> getUserInfo(@PathVariable Long userId){
        return ApiResponse.success(
                ResponseUserDto.from(userService.getUserInfo(userId)),
                "유저 정보 조회 성공");
    }

    //유저 닉네임 변경
    @Operation(summary = "[유저] 유저 닉네임 변경", description = "유저의 닉네임을 변경합니다.")
    @PutMapping("/nickname/{userId}")
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
    public ApiResponse<Page<ResponseUserDto>> getUsersWithRankingOfPoint(@RequestParam int size, @RequestParam int page){
        return ApiResponse.success(userService.getUsersWithRankingOfPoint(Pageable.ofSize(size).withPage(page)), "유저 랭킹 조회 성공");
    }

    @Operation(summary = "[유저] 유저 포인트 초기화", description = "유저의 포인트를 초기화합니다.")
    @PutMapping("/reset")
    public ApiResponse<Void> resetAllUserPoint(){
        userService.resetAllUserPoint();
        return ApiResponse.success(null, "유저 포인트 초기화 성공");
    }

    @Operation(summary = "[유저] 유저 포인트 업데이트", description = "유저의 포인트를 업데이트합니다.")
    @PutMapping("/all")
    public ApiResponse<Void> updateAllUserPoint(){
        userService.updateAllUserPoint();
        return ApiResponse.success(null, "유저 포인트 업데이트 성공");
    }


}
