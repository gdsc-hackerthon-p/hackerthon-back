package com.gdsc.hackerthon.auth.controller;

import com.gdsc.hackerthon.auth.component.JwtTokenProvider;
import com.gdsc.hackerthon.auth.dto.LoginUserDto;
import com.gdsc.hackerthon.auth.dto.ResponseJwtDto;
import com.gdsc.hackerthon.github.application.GithubService;
import com.gdsc.hackerthon.user.application.UserService;
import com.gdsc.hackerthon.user.domain.User;
import com.gdsc.hackerthon.user.dto.request.CreateUserDto;
import com.gdsc.hackerthon.user.dto.response.ResponseUserDto;
import com.gdsc.hackerthon.util.api.ApiResponse;
import com.gdsc.hackerthon.util.api.ResponseCode;
import com.gdsc.hackerthon.util.exception.UserException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.kohsuke.github.GHUser;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@Slf4j
@Tag(name = "Auth", description = "인증 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final UserService userService;
    private final GithubService githubService;
    private final JwtTokenProvider jwtTokenProvider;

    @Operation(summary = "[회원가입] 회원가입 및 토큰 발급", description = "신규 회원가입을 처리하고, 회원가입 성공 시 id를 반환합니다.")
    @PostMapping("/register")
    public ApiResponse<Long> saveUser(@Valid @RequestBody CreateUserDto createUserDto) throws IOException {
        GHUser githubUser = githubService.getGithubUser(createUserDto.getGithubId());
        log.info("githubUser: {}", githubUser);


        String encryptedPassword = userService.encryptPassword(createUserDto.getPassword());

        User user = User.builder()
                .username(createUserDto.getUsername())
                .githubId(createUserDto.getGithubId())
                .githubEmail(githubUser.getEmail())
                .commitStreak(0)
                .bio(githubUser.getBio())
                .nickname(createUserDto.getNickname())
                .password(encryptedPassword)
                .point(0)
                .profileImageUrl(githubUser.getAvatarUrl())
                .githubUrl(githubUser.getHtmlUrl().toString())
                .build();

        return ApiResponse.success(userService.createUserWithGithubAccount(user).getId() ,"유저 생성 성공");
    }

    @Operation(summary = "[인증] 로그인 및 토큰 발급", description = "로그인을 처리하고, 로그인 성공 시 id와 Jwt 토큰을 발급합니다.")
    @PostMapping("/login")
    public ApiResponse<ResponseJwtDto> login(@RequestBody LoginUserDto loginUserDto){
        Boolean isAuthorized = userService.login(loginUserDto.getUsername(), loginUserDto.getPassword());
        if (!isAuthorized) {
            throw new UserException(ResponseCode.USER_LOGIN_FAILURE);
        }
        User user = userService.getUserInfoWithUsername(loginUserDto.getUsername());
        String jwt = jwtTokenProvider.createToken(user.getId().toString());
        return ApiResponse.success(ResponseJwtDto.of(user.getId(), jwt), "로그인 성공");

    }

}
