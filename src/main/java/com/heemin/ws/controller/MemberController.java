package com.heemin.ws.controller;

import com.heemin.ws.model.dto.member.Member;
import com.heemin.ws.model.dto.member.SignupInfo;
import com.heemin.ws.model.service.MemberService;
import com.heemin.ws.support.Auth;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/members")
public class MemberController {
	MemberService memberService;
	
	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}
	
	// 이미 로그인된 현재 아이디를 기준으로 유저 정보 받아오기
	@GetMapping("")
	public ResponseEntity<Member> getLoggedInMember(HttpSession session){
		long memberId = -1;
		
		Member member = memberService.getById(memberId);
		return new ResponseEntity<Member>(member, HttpStatus.OK);
	}
	
	// 지정한 유저의 아이디를 기준으로 유저 정보 받아오기
	@GetMapping("/{id}")
	public ResponseEntity<Member> getMemberById(@PathVariable long id){
		Member member = memberService.getById(id);
		return new ResponseEntity<Member>(member, HttpStatus.OK);
	}
	
	// 입력받은 아이디를 기준으로 유저 정보 업데이트 진행하기
	@PutMapping("/{id}")
	public ResponseEntity<Void> updateMember(@RequestBody Member member){
		if (memberService.update(member))
			return new ResponseEntity<Void>(HttpStatus.OK);
		else
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@PostMapping
	public ResponseEntity<?> setup(@RequestBody SignupInfo info, @Auth Long memberId) {
		return memberService.setup(info, memberId).getResponse();
	}
}
