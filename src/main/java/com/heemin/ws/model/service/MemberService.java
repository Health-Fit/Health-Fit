package com.heemin.ws.model.service;

import com.heemin.ws.model.dao.MemberDao;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    private MemberDao memberDao;

    public MemberService(MemberDao memberDao) {
        this.memberDao = memberDao;
    }
}
