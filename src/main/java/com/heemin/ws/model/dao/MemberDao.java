package com.heemin.ws.model.dao;

import com.heemin.ws.model.dto.member.Member;

public interface MemberDao {

    Member selectByEmail(String email);
}
