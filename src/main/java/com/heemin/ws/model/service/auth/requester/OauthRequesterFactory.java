package com.heemin.ws.model.service.auth.requester;

import org.springframework.stereotype.Component;

@Component
public class OauthRequesterFactory {

    private final KakaoRequester kakaoRequester;
    private final GoogleRequester googleRequester;
    private final NaverRequester naverRequester;

    public OauthRequesterFactory(KakaoRequester kakaoRequester, GoogleRequester googleRequester,
                                 NaverRequester naverRequester) {
        this.kakaoRequester = kakaoRequester;
        this.googleRequester = googleRequester;
        this.naverRequester = naverRequester;
    }

    public OauthRequester getRequester(String type) {
        OauthLoginType loginType = OauthLoginType.valueOf(type.toUpperCase());

        switch (loginType) {
            case KAKAO:
                return kakaoRequester;
            case GOOGLE:
                return googleRequester;
            case NAVER:
                return naverRequester;
            default:
                throw new IllegalArgumentException("유효하지 않은 소셜 로그인 타입입니다.");
        }
    }

    public enum OauthLoginType {
        KAKAO,
        GOOGLE,
        NAVER
    }
}
