package com.heemin.ws.model.service.auth.requester;

import com.heemin.ws.model.dto.auth.OauthToken;
import com.heemin.ws.model.dto.member.Member;
import com.heemin.ws.model.service.auth.OauthProperties;
import com.heemin.ws.model.service.auth.OauthProperties.Naver;
import org.springframework.stereotype.Component;

@Component
public class NaverRequester implements OauthRequester {

    private final Naver naver;

    public NaverRequester(OauthProperties oauthProperties) {
        this.naver = oauthProperties.getNaver();
    }

    @Override
    public OauthToken getToken(String code) {
        return null;
    }

    @Override
    public Member getMemberInfo(OauthToken token) {
        return null;
    }
}
