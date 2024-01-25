package com.example.dailyworknotion.controller.dto.member;

import com.example.dailyworknotion.domain.Member;
import com.example.dailyworknotion.validGroup.ToContentValidGroup;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MemberRequest {

    @NotNull(groups = ToContentValidGroup.class, message = "아이디를 입력하세요.")
    private Long id;

    @NotNull(groups = ToContentValidGroup.class, message = "이메일을 입력하세요.")
    private String email;

    @NotNull(groups = ToContentValidGroup.class, message = "수신여부를 선택하세요.")
    private boolean isAcceptEmail;

    @NotNull(groups = ToContentValidGroup.class, message = "출근 시간을 입력하세요.")
    private LocalDateTime goOfficeHour;


    public Member toTarget() {
        return Member.builder()
                .id(id)
                .email(email)
                .isAcceptEmail(isAcceptEmail)
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