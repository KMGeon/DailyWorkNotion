package com.example.dailyworknotion.controller;

import com.example.dailyworknotion.ApiResponse;
import com.example.dailyworknotion.application.MemberService;
import com.example.dailyworknotion.controller.dto.member.request.MemberRequest;
import com.example.dailyworknotion.controller.dto.member.response.MemberResponse;
import com.example.dailyworknotion.domain.Member;
import com.example.dailyworknotion.controller.dto.validGroup.ToContentValidGroup;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
@RequiredArgsConstructor
public class MemberController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());
    private final MemberService memberService;

    @PostMapping("signUp")
    public ApiResponse<MemberResponse> signUpMember(@Validated(ToContentValidGroup.class) @RequestBody MemberRequest memberRequest,
                                            HttpServletRequest request) {

        logger.info("[" + request.getRemoteAddr() + "] ====== /notion/database [" + getClass().getSimpleName() + ".getNotionDatabase()] start ======");

        logger.info("[{}] ====== /notion/database [{}.email : {}]", request.getRemoteAddr(), getClass().getSimpleName(), memberRequest.getEmail());
        logger.info("[{}] ====== /notion/database [{}.goOfficeHour : {}]", request.getRemoteAddr(), getClass().getSimpleName(), memberRequest.getGoOfficeHour());
        logger.info("[{}] ====== /notion/database [{}.isAcceptEmail : {}]", request.getRemoteAddr(), getClass().getSimpleName(), memberRequest.isAcceptEmail());

        Member contentMember = memberRequest.toContent();
        MemberResponse memberResponse = memberService.signUp(contentMember);

        logger.info("[" + request.getRemoteAddr() + "] ====== /notion/database [" + getClass().getSimpleName() + ".getNotionDatabase()] end ======");
        return ApiResponse.success(HttpStatus.CREATED, memberResponse);
    }

}
