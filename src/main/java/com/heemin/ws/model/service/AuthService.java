package com.heemin.ws.model.service;

import com.heemin.ws.model.dao.AuthDao;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private AuthDao authDao;

    public AuthService(AuthDao authDao) {
        this.authDao = authDao;
    }
}
