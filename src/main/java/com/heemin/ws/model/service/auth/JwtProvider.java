package com.heemin.ws.model.service.auth;

import com.heemin.ws.model.dto.auth.Jwt;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public class JwtProvider {

    private static final byte[] secret = "bang-min-kyu-cho-hee-ju-jeon-yeo-po-and-bang-to-ma".getBytes();
    private final Key key = Keys.hmacShaKeyFor(secret);

    public String createToken(Map<String, Object> claims, Date expireDate) { // 토큰 생성 메서드
        return Jwts
                .builder()                 // Jwt 토큰 빌더 생성
                .setClaims(claims)         // 클레임 정보 설정
                .setExpiration(expireDate) // 만료 시간 설정
                .signWith(key)             // 서명
                .compact();                // 문자열 형태로 반환
    }

    public Claims getClaims(String token) { // Jwt 토큰 파싱해 클레임 정보 반환하는 메서드
        return Jwts
                .parserBuilder()       // 파서 빌더 생성
                .setSigningKey(key)    // 서명한 키 설정
                .build()
                .parseClaimsJws(token) // 토큰을 파싱
                .getBody();            // 클레임 정보 가져옴
    }

    public Jwt createJwt(Map<String, Object> claims) { // accessToken과 refreshToken 생성하는 메서드
        String accessToken = createToken(claims, getExpireDateAccessToken());
        String refreshToken = createToken(claims, getExpireDateRefreshToken());
        return new Jwt(accessToken, refreshToken);
    }

    public Jwt reissueAccessToken(Map<String, Object> claims, String refreshToken) { // accessToken 재발급 메서드
        String accessToken = createToken(claims, getExpireDateAccessToken());
        return new Jwt(accessToken, refreshToken);
    }

    public Date getExpireDateAccessToken() {
        long expireTimeMils = 1000L * 60 * 60; // 1시간
        return new Date(System.currentTimeMillis() + expireTimeMils);
    }

    public Date getExpireDateRefreshToken() {
        long expireTimeMils = 1000L * 60 * 60 * 24 * 30; // 30일
        return new Date(System.currentTimeMillis() + expireTimeMils);
    }

    public long getAccessTokenExpiration(String accessToken) {
        Claims claims = getClaims(accessToken);

        return claims.getExpiration().getTime() - System.currentTimeMillis();
    }
}
