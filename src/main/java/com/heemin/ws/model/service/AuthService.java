package com.heemin.ws.model.service;

import com.heemin.ws.model.dao.AuthDao;
import com.heemin.ws.model.dao.MemberDao;
import com.heemin.ws.model.dto.Response;
import com.heemin.ws.model.dto.auth.Jwt;
import com.heemin.ws.model.dto.auth.JwtProvider;
import com.heemin.ws.model.dto.auth.request.LoginRequest;
import com.heemin.ws.model.dto.member.Member;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private AuthDao authDao;
    private MemberDao memberDao;
    private JwtProvider jwtProvider;

    public AuthService(AuthDao authDao, MemberDao memberDao, JwtProvider jwtProvider) {
        this.authDao = authDao;
        this.memberDao = memberDao;
        this.jwtProvider = jwtProvider;
    }

    public Response login(LoginRequest request) {
        Member member = memberDao.selectByEmail(request.getEmail());
        if(!verifyPassword(member, request.getPassword())) {
            return new Response("이메일이나 비밀번호가 틀렸습니다.", 401);
        }
        Jwt jwt = jwtProvider.createJwt(createClaims(member));
        authDao.insertRefreshToken(member.getId(), jwt.getRefreshToken());
        return new Response(jwt, 200);
    }

    private boolean verifyPassword(Member member, String password) {
        return member != null && member.getPassword().equals(password);
    }

    private Map<String, Object> createClaims(Member member) {
        return Map.of("memberId", member.getId());
    }
}
