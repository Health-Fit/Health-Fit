package com.heemin.ws.model.service.auth;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;

@ConfigurationProperties(prefix = "oauth2")
public class OauthProperties {

    private final Kakao kakao;
    private final Google google;
    private final Naver naver;

    @ConstructorBinding
    public OauthProperties(Kakao kakao, Google google, Naver naver) {
        this.kakao = kakao;
        this.google = google;
        this.naver = naver;
    }

    public Kakao getKakao() {
        return kakao;
    }

    public Google getGoogle() {
        return google;
    }

    public Naver getNaver() {
        return naver;
    }

    public static class Kakao {
        private final User user;
        private final Provider provider;

        public Kakao(User user, Provider provider) {
            this.user = user;
            this.provider = provider;
        }

        public User getUser() {
            return user;
        }

        public Provider getProvider() {
            return provider;
        }
    }

    public static class Google {
        private final User user;
        private final Provider provider;

        public Google(User user, Provider provider) {
            this.user = user;
            this.provider = provider;
        }

        public User getUser() {
            return user;
        }

        public Provider getProvider() {
            return provider;
        }
    }

    public static class Naver {
        private final User user;
        private final Provider provider;

        public Naver(User user, Provider provider) {
            this.user = user;
            this.provider = provider;
        }

        public User getUser() {
            return user;
        }

        public Provider getProvider() {
            return provider;
        }
    }

    public static class User {
        private final String clientId;
        private final String clientSecret;
        private final String redirectUri;

        public User(String clientId, String clientSecret, String redirectUri) {
            this.clientId = clientId;
            this.clientSecret = clientSecret;
            this.redirectUri = redirectUri;
        }

        public String getClientId() {
            return clientId;
        }

        public String getClientSecret() {
            return clientSecret;
        }

        public String getRedirectUri() {
            return redirectUri;
        }
    }

    public static class Provider {
        private final String tokenUri;
        private final String userInfoUri;

        public Provider(String tokenUri, String userInfoUri) {
            this.tokenUri = tokenUri;
            this.userInfoUri = userInfoUri;
        }

        public String getTokenUri() {
            return tokenUri;
        }

        public String getUserInfoUri() {
            return userInfoUri;
        }
    }
}

