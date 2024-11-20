package com.heemin.ws.model.service.auth;

import com.heemin.ws.model.dao.AuthDao;
import com.heemin.ws.model.dao.MemberDao;
import com.heemin.ws.model.dto.Response;
import com.heemin.ws.model.dto.auth.Jwt;
import com.heemin.ws.model.dto.auth.OauthToken;
import com.heemin.ws.model.dto.member.Member;
import com.heemin.ws.model.service.auth.requester.OauthRequester;
import com.heemin.ws.model.service.auth.requester.OauthRequesterFactory;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Map;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService {
    private final AuthDao authDao;
    private final MemberDao memberDao;
    private final JwtProvider jwtProvider;
    private final OauthRequesterFactory oauthRequesterFactory;
    private final RedisTemplate<String, Object> redisTemplate;

    public AuthService(AuthDao authDao, MemberDao memberDao, JwtProvider jwtProvider,
                       OauthRequesterFactory oauthRequesterFactory, RedisTemplate<String, Object> redisTemplate) {
        this.authDao = authDao;
        this.memberDao = memberDao;
        this.jwtProvider = jwtProvider;
        this.oauthRequesterFactory = oauthRequesterFactory;
        this.redisTemplate = redisTemplate;
    }

    @Transactional
    public Response login(String type, String code) {
        // type 이름으로 Reuqester 가져오기
        OauthRequester requester = oauthRequesterFactory.getRequester(type);

        // 소셜 서버에 접근해서 토큰과 회원 정보 가져오기
        OauthToken oauthToken = requester.getToken(code);
        Member memberInfo = requester.getMemberInfo(oauthToken);

        // db에 없는 회원이면 회원가입
        // 회원가입이라면, 운동영상 정보 가져오기 (선호 영상 선택)
        Member member = getMember(memberInfo.getEmail());
        int status = 200;

        if (member == null) {
            member = memberInfo;
            member.setId(signup(member));
            status = 201;
        }

        // 자체 Jwt 토큰 만들고 refreshToken 저장
        Jwt jwt = jwtProvider.createJwt(Map.of("memberId", member.getId()));
        authDao.insertRefreshToken(member.getId(), jwt.getRefreshToken());

        // 회원 가입인 경우, 운동 영상 정보 + Jwt 반환
        return new Response(jwt, status);
    }

    private Member getMember(String email) {
        return memberDao.selectByEmail(email);
    }

    private int signup(Member member) {
        return memberDao.insert(member);
    }

    @Transactional
    public Response logout(HttpServletRequest request, long memberId) {
        JwtExtractor.extract(request).ifPresent(token -> {
            long expiration = jwtProvider.getAccessTokenExpiration(token);
            redisTemplate.opsForValue().set(token, "logout", expiration);
        });
        authDao.deleteRefreshToken(memberId);
        return new Response(200);
    }

    public Response reissueAccessToken(Jwt jwt) {
        Claims claims = jwtProvider.getClaims(jwt.getRefreshToken());
        long memberId = Long.valueOf(claims.get("memberId").toString());

        if (!authDao.existByMemberId(memberId)) {
            return new Response("유효하지 않은 refresh token", 401);
        }
        return new Response(jwtProvider.reissueAccessToken(Map.of("memberId", memberId), jwt.getRefreshToken()), 200);
    }
}
