package com.example.dailyworknotion.controller.dto.member;

import com.example.dailyworknotion.domain.Member;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MemberRequest {

    private Long id;

    private String email;

    private boolean isAcceptEmail;

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