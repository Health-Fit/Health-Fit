package com.heemin.ws.controller;

import static com.heemin.ws.controller.MemberManager.getMemberId;

import com.heemin.ws.model.dto.auth.Jwt;
import com.heemin.ws.model.service.auth.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;

@RequestMapping("/api/auth")
@RestController
public class AuthController {
    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/login/{type}")
    public ResponseEntity<?> login(@PathVariable String type, @RequestParam String code) {
        return authService.login(type, code).getResponse();
    }

    @PostMapping("/access-token")
    public ResponseEntity<?> reissueAccessToken(@RequestBody Jwt jwt) {
        return authService.reissueAccessToken(jwt).getResponse();
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request, NativeWebRequest webRequest) {
        long memberId = getMemberId(webRequest);
        return authService.logout(request, memberId).getResponse();
    }
}
