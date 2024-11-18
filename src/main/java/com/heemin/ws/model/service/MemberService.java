package com.heemin.ws.model.service;

import com.heemin.ws.model.dao.MemberDao;
import com.heemin.ws.model.dto.member.Member;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    private final MemberDao memberDao;

    public MemberService(MemberDao memberDao) {
        this.memberDao = memberDao;
    }

    public Member getById(long id) {
        return memberDao.selectById(id);
    }

    public boolean update(Member member) {
        return memberDao.update(member) == 1;
    }
}
