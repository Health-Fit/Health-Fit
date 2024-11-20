package com.heemin.ws.model.dao;

import org.apache.ibatis.annotations.Param;

public interface AuthDao {

    int insertRefreshToken(@Param("memberId") long memberId, @Param("refreshToken") String refreshToken);

    int deleteRefreshToken(long memberId);

    boolean existByMemberId(long memberId);
}
