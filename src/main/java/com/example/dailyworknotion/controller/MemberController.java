package com.example.dailyworknotion.controller;

import com.example.dailyworknotion.ApiResponse;
import com.example.dailyworknotion.controller.dto.member.MemberRequest;
import com.example.dailyworknotion.domain.Member;
import com.example.dailyworknotion.repository.MemberRepository;
import com.example.dailyworknotion.validGroup.ToContentValidGroup;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
public class MemberController {

    private final MemberRepository memberRepository;

    public MemberController(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    private final Logger logger = LoggerFactory.getLogger(MemberController.class);

    @PostMapping("signUp")
    public ApiResponse<Member> signUpMember(@Validated(ToContentValidGroup.class) MemberRequest memberRequest,
                                            HttpServletRequest request) {
        logger.info("[" + request.getRemoteAddr() + "] ====== /notion/database [" + getClass().getSimpleName() + ".getNotionDatabase()] start ======");
        Member member = Member.of(memberRequest.toContent());
        Member save = memberRepository.save(member);

        logger.info("[" + request.getRemoteAddr() + "] ====== /notion/database [" + getClass().getSimpleName() + ".getNotionDatabase()] start ======");
        return ApiResponse.success(HttpStatus.CREATED, save);
    }

}
