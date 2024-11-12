package com.heemin.ws.model.dao;

public interface AuthDao {

    int insertRefreshToken(long memberId, String refreshToken);
}
