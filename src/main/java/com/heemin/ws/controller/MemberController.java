package com.heemin.ws.controller;

import com.heemin.ws.model.dto.member.SignupInfo;
import com.heemin.ws.model.service.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberController {
    private MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

//    @PostMapping("/api/members")
//    public ResponseEntity<?> setup(@RequestBody SignupInfo info) {
//
//    }
}
