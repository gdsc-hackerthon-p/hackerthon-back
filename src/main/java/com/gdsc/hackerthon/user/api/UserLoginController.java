package com.gdsc.hackerthon.user.api;
/*
import com.gdsc.hackerthon.user.application.WebClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@Slf4j
@RequiredArgsConstructor
public class UserLoginController {

    private final WebClientService webClientService;

    @GetMapping("/oauth2/redirect")
    private String githubLogin(@RequestParam String code){
        String accessToken = webClientService.getAccessToken(code,"https://github.com/login/oauth/access_token");
        return "redirect:/githubLogin/success?access_token="+accessToken;
    }

}
*/
