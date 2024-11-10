package com.heemin.ws.controller;

import com.heemin.ws.model.service.MemberService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberController {
    private MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}
