package com.gdsc.hackerthon.user.application;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
/*
@Service
@RequiredArgsConstructor
public class WebClientService {

    @Value("${github.client-id}")
    private String clientId;
    @Value("${github.client-secret}")
    private String clientSecret;

    //private final WebClient webClient;
    public String getAccessToken(String code,String uri) {

        AccessTokenRequest requestBody = AccessTokenRequest.builder()
                .clientId(clientId)
                .clientSecret(clientSecret)
                .code(code)
                .build();

        String response = webClient.post()
                .uri(uri)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .bodyValue(requestBody)
                .retrieve()
                .toEntity(String.class)
                .block().getBody();


        return TextParsingUtil.parsingFormData(response).get("access_token");
    }

}
*/