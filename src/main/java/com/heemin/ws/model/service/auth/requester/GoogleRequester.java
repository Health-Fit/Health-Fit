package com.heemin.ws.model.service.auth.requester;

import com.heemin.ws.model.dto.auth.OauthToken;
import com.heemin.ws.model.dto.member.Member;
import com.heemin.ws.model.service.auth.OauthProperties;
import com.heemin.ws.model.service.auth.OauthProperties.Google;
import org.springframework.stereotype.Component;

@Component
public class GoogleRequester implements OauthRequester {

    private final Google google;

    public GoogleRequester(OauthProperties oauthProperties) {
        this.google = oauthProperties.getGoogle();
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
