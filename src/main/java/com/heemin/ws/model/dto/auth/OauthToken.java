package com.heemin.ws.model.dto.auth;

public class OauthToken {

    private String tokenType;
    private String accessToken;

    public OauthToken() {
    }

    public OauthToken(String tokenType, String accessToken) {
        this.tokenType = tokenType;
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public String getAccessToken() {
        return accessToken;
    }
}
