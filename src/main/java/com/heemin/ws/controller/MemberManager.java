package com.heemin.ws.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.NativeWebRequest;

public class MemberManager {
    static public long getMemberId(NativeWebRequest webRequest){
        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
        Object loginMember = request.getAttribute("memberId");
        long memberId = -1;
        if (loginMember != null)
            memberId = Long.valueOf(loginMember.toString());
        // 테스트용으로 1로 고정, 후에는 실제 로그인된 아이디 토대로 받아올 것
        return 1;
    }
}
