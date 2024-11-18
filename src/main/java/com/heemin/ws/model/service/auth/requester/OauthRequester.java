package com.heemin.ws.model.service.auth.requester;

import com.heemin.ws.model.dto.auth.OauthToken;
import com.heemin.ws.model.dto.member.Member;

public interface OauthRequester {

    OauthToken getToken(String code);
    Member getMemberInfo(OauthToken token);
}
