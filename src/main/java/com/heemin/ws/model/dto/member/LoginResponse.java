package com.heemin.ws.model.dto.member;

import com.heemin.ws.model.dto.auth.Jwt;

public class LoginResponse {

    private Jwt jwt;
    private Member member;

    public LoginResponse() {
    }

    public LoginResponse(Jwt jwt, Member member) {
        this.jwt = jwt;
        this.member = member;
    }

    public Jwt getJwt() {
        return jwt;
    }

    public void setJwt(Jwt jwt) {
        this.jwt = jwt;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }
}
