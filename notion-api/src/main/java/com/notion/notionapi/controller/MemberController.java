package com.notion.notionapi.controller;

import com.notion.notionapi.ApiResponse;
import com.notion.notionapi.application.MemberService;
import com.notion.notionapi.controller.dto.member.request.MemberRequest;
import com.notion.notionapi.controller.dto.member.response.MemberResponse;
import com.notion.notionapi.validator.ToContentValidGroup;
import com.notion.notioncore.domain.Member;
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
    public ApiResponse<MemberResponse> signUpMember(
            @Validated(ToContentValidGroup.class)
            @RequestBody MemberRequest memberRequest) {

        logger.info("====== /notion/database [{}.getNotionDatabase()] start ======", getClass().getSimpleName());

        logger.info("[{}] ====== /notion/database [email : {}]", getClass().getSimpleName(), memberRequest.getEmail());
        logger.info("[{}] ====== /notion/database [goOfficeHour : {}]", getClass().getSimpleName(), memberRequest.getGoOfficeHour());
        logger.info("[{}] ====== /notion/database [isAcceptEmail : {}]", getClass().getSimpleName(), memberRequest.isAcceptEmail());

        Member contentMember = memberRequest.toContent();
        MemberResponse memberResponse = memberService.signUp(contentMember);

        logger.info("====== /notion/database [{}.getNotionDatabase()] end ======", getClass().getSimpleName());
        return ApiResponse.success(HttpStatus.CREATED, memberResponse);

    }

}
