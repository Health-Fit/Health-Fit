package com.heemin.ws.controller;

import com.heemin.ws.model.service.AuthService;
import org.springframework.stereotype.Controller;

@Controller
public class AuthController {
    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }
}
