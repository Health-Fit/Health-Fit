package com.heemin.ws.model.dao;

import com.heemin.ws.model.dto.member.Member;
import org.apache.ibatis.annotations.Param;

public interface MemberDao {

    Member selectByEmail(String email);

    int insert(Member member);

    public Member selectById(long id);

    public int update(Member member);

    int updateNickname(@Param("memberId") long memberId, @Param("nickname") String nickname);
}
