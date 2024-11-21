package com.heemin.ws.controller;

import com.heemin.ws.model.dto.member.Member;
import com.heemin.ws.model.dto.member.SignupInfo;
import com.heemin.ws.model.service.MemberService;
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
import org.springframework.web.context.request.NativeWebRequest;

import static com.heemin.ws.controller.MemberManager.getMemberId;

@RestController
@RequestMapping("api/members")
public class MemberController {
    MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    // 이미 로그인된 현재 아이디를 기준으로 유저 정보 받아오기
    @GetMapping("")
    public ResponseEntity<?> getLoggedInMember(NativeWebRequest webRequest) {
        long memberId = getMemberId(webRequest);
        // 현재 로그인된 정보가 없는 경우 잘못된 요청 반환
        if (memberId == -1)
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);

        Member member = memberService.getById(memberId);
        if (member == null)
            return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<Member>(member, HttpStatus.OK);
    }

    // 지정한 유저의 아이디를 기준으로 유저 정보 받아오기
    @GetMapping("/{id}")
    public ResponseEntity<?> getMemberById(@PathVariable long id) {
        Member member = memberService.getById(id);

        // 해당 멤버가 존재하지 않는 경우 자료 없음 내보내기
        if (member == null)
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<Member>(member, HttpStatus.OK);
    }

    // 현재 로그인한 멤버 아이디 기준으로 유저 정보 변경하기
    @PutMapping("")
    public ResponseEntity<?> updateMember(@RequestBody Member member, NativeWebRequest webRequest) {
        long memberId = getMemberId(webRequest);
        // 현재 로그인된 정보가 없다면 잘못된 요청 반환
        if (memberId == -1)
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);

        // 유저 정보 업데이트 진행
        member.setId(memberId);
        if (memberService.update(member))
            return new ResponseEntity<Void>(HttpStatus.OK);
        else
            return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping
    public ResponseEntity<?> setup(@RequestBody SignupInfo info, Long memberId) {
        return memberService.setup(info, memberId).getResponse();
    }
}
