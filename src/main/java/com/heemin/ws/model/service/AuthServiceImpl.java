package com.heemin.ws.model.service;

import com.heemin.ws.model.dao.AuthDao;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    private AuthDao authDao;

    public AuthServiceImpl(AuthDao authDao) {
        this.authDao = authDao;
    }
}
