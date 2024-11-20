package com.heemin.ws.model.service;

import com.heemin.ws.model.dao.CategoryDao;
import com.heemin.ws.model.dao.MemberDao;
import com.heemin.ws.model.dto.Response;
import com.heemin.ws.model.dto.member.Member;
import com.heemin.ws.model.dto.member.SignupInfo;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    private final MemberDao memberDao;
    private final CategoryDao categoryDao;

    public MemberService(MemberDao memberDao, CategoryDao categoryDao) {
        this.memberDao = memberDao;
        this.categoryDao = categoryDao;
    }

    public Member getById(long id) {
        return memberDao.selectById(id);
    }

    public boolean update(Member member) {
        return memberDao.update(member) == 1;
    }

    public Response setup(SignupInfo info, long memberId) {
        memberDao.updateNickname(memberId, info.getNickname());
        categoryDao.insertMemberCategory(memberId, info.getCategoryIds());
        return new Response(200);
    }
}
