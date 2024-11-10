package com.heemin.ws.controller;

import com.heemin.ws.model.service.AuthService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }
}
