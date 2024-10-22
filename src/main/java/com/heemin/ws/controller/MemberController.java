package com.heemin.ws.controller;

import com.heemin.ws.model.service.MemberService;
import org.springframework.stereotype.Controller;

@Controller
public class MemberController {
    private MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}
