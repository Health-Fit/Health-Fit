package com.heemin.ws.model.service.auth.requester;

import com.heemin.ws.model.dto.auth.OauthToken;
import com.heemin.ws.model.dto.member.Member;
import com.heemin.ws.model.service.auth.OauthProperties;
import com.heemin.ws.model.service.auth.OauthProperties.Kakao;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class KakaoRequester implements OauthRequester {

    private final Kakao kakao;

    public KakaoRequester(OauthProperties oauthProperties) {
        this.kakao = oauthProperties.getKakao();
    }

    @Override
    public OauthToken getToken(final String code) {

        return  WebClient.create()
                .post()
                .uri(kakao.getProvider().getTokenUri())
                .headers(header -> {
                    header.add("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
                })
                .bodyValue(createTokenRequestBody(code))
                .retrieve()
                .bodyToMono(OauthToken.class)
                .block();

    }

    private MultiValueMap<String, String> createTokenRequestBody(final String code) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();

        params.add("grant_type", "authorization_code");
        params.add("client_id", kakao.getUser().getClientId());
        params.add("redirect_uri", kakao.getUser().getRedirectUri());
        params.add("code", code);

        return params;
    }

    @Override
    public Member getMemberInfo(OauthToken token) {
        String uri = UriComponentsBuilder.fromHttpUrl(kakao.getProvider().getUserInfoUri())
                .queryParam("property_keys",
                        "[\"kakao_account.profile\", \"kakao_account.name\", \"kakao_account.email\", \"kakao_account.birthday\", \"kakao_account.gender\"]")
                .build()
                .toString();

        Map<String, Object> response = WebClient.create()
                .get()
                .uri(uri)
                .headers(header -> {
                    header.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
                    header.setBearerAuth(token.getAccessToken());
                })
                .retrieve()
                .bodyToMono(Map.class)
                .block();

        Map<String, Object> kakaoAccount = (Map<String, Object>) response.get("kakao_account");

        return parseResponseBody(kakaoAccount);
    }

    private Member parseResponseBody(Map<String, Object> kakaoAccount) {
        Member member = new Member();

        member.setEmail(kakaoAccount.get("email").toString());
        member.setName(kakaoAccount.get("name").toString());
        member.setGender(kakaoAccount.get("gender").toString());
        member.setProfileImg(((Map<String, Object>)kakaoAccount.get("profile")).get("profile_image_url").toString());

        String birthStr = kakaoAccount.getOrDefault("birthyear", "2024").toString() + kakaoAccount.getOrDefault("birthday", "1118").toString();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate localDate = LocalDate.parse(birthStr, formatter);

        member.setBirth(localDate);

        return member;
    }
}
