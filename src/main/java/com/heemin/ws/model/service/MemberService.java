package com.ssafy.ssafit.model.service;

import org.springframework.stereotype.Service;

import com.ssafy.ssafit.model.dao.MemberDao;
import com.ssafy.ssafit.model.dto.Member;

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
