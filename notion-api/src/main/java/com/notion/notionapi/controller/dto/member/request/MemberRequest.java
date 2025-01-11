package com.notion.notionapi.controller.dto.member.request;

import com.notion.notionapi.validator.ToContentValidGroup;
import com.notion.notionapi.validator.ToTargetValidationGroup;
import com.notion.notioncore.domain.Member;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalTime;

@Getter
public class MemberRequest {

    @NotNull(groups = {ToTargetValidationGroup.class}, message = "아이디를 입력하세요.")
    private Long id;

    @NotNull(groups = {ToTargetValidationGroup.class, ToContentValidGroup.class}, message = "이메일을 입력하세요.")
    private String email;

    @NotNull(groups = ToContentValidGroup.class, message = "수신여부를 선택하세요.")
    private boolean isAcceptEmail;

    @DateTimeFormat(pattern = "HH:mm")
    @NotNull(groups = ToContentValidGroup.class, message = "출근 시간을 입력하세요.")
    private LocalTime goOfficeHour;


    public Member toTarget() {
        return Member.builder()
                .email(email)
                .build();
    }
    public Member toContent() {
        return Member.builder()
                .email(this.email)
                .isAcceptEmail(this.isAcceptEmail)
                .officeGoHour(this.goOfficeHour)
                .build();
    }


}